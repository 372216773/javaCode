package threadPool.threadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue 是一种特殊的队列. 没有容量. 只有当线程取任务时，才会将任务放入该阻塞队列中
 * 将任务放入(put)时, 如果没有线程来取任务, 就会阻塞住, 当有线程来取任务时, 才会将任务放入该阻塞队列中, 执行任务
 */
@Slf4j
public class synchronousQueueDemo {
    public static void main(String[] args) {

        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.debug("start put(1)，如果没有线程来取任务，这个线程就会阻塞住");
                    synchronousQueue.put(1);
                    log.debug("任务被取走了");

                    TimeUnit.SECONDS.sleep(1);

                    log.debug("start put(2)，如果没有线程来取任务，这个线程就会阻塞住");
                    synchronousQueue.put(2);
                    log.debug("任务被取走了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.debug("{}", synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.debug("{}", synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}