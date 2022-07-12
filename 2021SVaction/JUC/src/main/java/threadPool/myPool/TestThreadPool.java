package threadPool.myPool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class TestThreadPool {
    public static void main(String[] args) {
        int queueCapacity = 5;
        int coreSize = 2;
        long timeout = 2;
        long taskTimeout = 1;
        ThreadPool threadPool = new ThreadPool(queueCapacity, coreSize, timeout, (queue, task) -> {
            log.debug("当阻塞队列满的时候，对与任务的处理是一直等待");
            queue.put(task);
            /*log.debug("当阻塞队列满的时候，对于任务的处理是超时等待");
            queue.put(task, taskTimeout, TimeUnit.SECONDS);*/
            /*log.debug("当队列满的时候，对与任务的处理是放弃执行任务");*/
            /*log.debug("当阻塞队列满的时候，对与任务的处理是抛出异常，后边的任务不再执行");
            throw new RuntimeException("任务添加失败");*/
            /*log.debug("当阻塞队列满的时候，对与任务的处理是让调用者自己执行任务，不再使用 worker 线程执行任务");
            task.run();*/
        });

        int taskNum = 10;
        log.debug("一共{}个任务，有最多{}个worker可以执行任务，多余的存放在阻塞队列中，最多放 {}，剩下 {} 会一直等待进入阻塞队列", taskNum, coreSize, queueCapacity, (taskNum - queueCapacity - coreSize));
        for (int i = 0; i < taskNum; i++) {
            int num = i;
            threadPool.execute(() -> {
                log.debug("task{} start", num);
                log.debug("task{} is running", num);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("task{} end", num);
            });
        }
    }
}

//函数式接口
@FunctionalInterface
interface RejectPolicy<T> {

    /**
     * 参数的选择: 由于拒绝策略是为了解决，当阻塞队列满时，对多余任务的处理方式
     *
     * @param queue 阻塞队列
     * @param task  任务
     */
    void reject(BlockingQueue<T> queue, T task);
}

@Slf4j
class ThreadPool {
    private BlockingQueue<Runnable> blockingQueue;

    private int coreSize;

    private HashSet<Worker> workers = new HashSet<>();

    //超时时间
    private long timeout;

    //进行时间单位转换
    private TimeUnit unit;

    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int queueCapacity, int coreSize, long timeout, RejectPolicy<Runnable> rejectPolicy) {
        this.blockingQueue = new BlockingQueue<>(queueCapacity);
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = TimeUnit.SECONDS;
        this.rejectPolicy = rejectPolicy;
    }

    //包工头
    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                log.debug("worker 的数量小于 coreSize");

                Worker worker = new Worker(task);

                workers.add(worker);
                log.debug("添加 worker. worker 数量: {}", workers.size());

                log.debug("worker start work");
                worker.start();
            } else {
                blockingQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    //工人
    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (task != null || (task = blockingQueue.take(timeout, unit)) != null) {
                task.run();

                //任务执行完毕，将task置为null，以接受新的任务
                log.debug("任务执行完毕，将task置为null，以接受新的任务");
                task = null;
            }

            log.debug("任务执行完毕，任务队列为空，不需要多余线程，移除 worker 线程");
            workers.remove(this);
        }
    }
}

@Slf4j
class BlockingQueue<T> {

    //任务队列
    private Deque<T> tasksQueue = new ArrayDeque<>();

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition fullWait = reentrantLock.newCondition();

    private Condition emptyWait = reentrantLock.newCondition();

    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T task) {
        log.debug("向阻塞队列中添加任务 {} 拿到了锁", Thread.currentThread().getName());
        reentrantLock.lock();

        try {
            while (tasksQueue.size() == capacity) {
                try {
                    log.debug("任务队列已满，进入 fullWait 中进行等待");
                    fullWait.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            tasksQueue.addLast(task);

            emptyWait.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean put(T task, long timeout, TimeUnit unit) {
        reentrantLock.lock();

        long nanos = unit.toNanos(timeout);

        try {
            while (tasksQueue.size() >= capacity) {
                if (nanos <= 0) {
                    log.debug("在规定时间内还未加入任务队列，直接退出");
                    return false;
                }

                try {
                    nanos = fullWait.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            tasksQueue.addLast(task);
            log.debug("在规定时间内，成功添加任务，并唤醒 emptyWait 中的线程.当前总任务数: {}", tasksQueue.size());
            emptyWait.signal();
            return true;
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * 取任务。未带超时时间，如果一直没有新的任务，就会一直等待
     *
     * @return
     */
    public T take() {
        log.debug("从阻塞队列中获得任务 {}拿到了锁", Thread.currentThread().getName());
        reentrantLock.lock();

        try {
            while (tasksQueue.isEmpty()) {
                try {
                    log.debug("任务队列为空，进入 emptyWait 中进行等待");
                    emptyWait.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            T task = tasksQueue.removeFirst();

            fullWait.signal();

            return task;
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * 取任务。带有超时时间，如果在规定时间内还没有新的任务进来，不进行等待，直接退出
     *
     * @param timeout
     * @param unit
     * @return
     */
    public T take(long timeout, TimeUnit unit) {
        reentrantLock.lock();

        long nanos = unit.toNanos(timeout);

        try {
            while (tasksQueue.isEmpty()) {
                if (nanos <= 0) {
                    log.debug("超过规定时间任务队列还是为空，直接退出，返回 null");
                    return null;
                }

                try {
                    nanos = emptyWait.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            T task = tasksQueue.removeFirst();
            log.debug("在规定时间内，成功获取到任务，并唤醒 fullWait 中的线程,此时任务队列中的任务个数为: {}", tasksQueue.size());

            fullWait.signal();

            return task;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int size() {
        return capacity;
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        reentrantLock.lock();

        try {
            if (tasksQueue.size() >= capacity) {
                rejectPolicy.reject(this, task);
            } else {
                tasksQueue.addLast(task);
                log.debug("task 可以加入任务队列, 当前任务队列存放的任务个数为 {}", tasksQueue.size());

                emptyWait.signal();
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}