package threadPool.threadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单线程执行器
 * 1. 多个任务会排队执行. 线程数固定为1, 任务数多于1时, 会放入无界队列排队. 任务执行完毕, 这唯一的线程也不会被释放
 * 2. 当线程正在执行的任务发生错误时，如果是自己创建的线程，该任务和剩余的任务就无法再继续运行下去。
 * 而 SingleThread 会创建一个新线程，继续执行任务队列中剩余的任务。
 */
@Slf4j
public class singleThreadPoolDemo {
    public static void main(String[] args) {
        //TestSingleThreadPoolWIthException();
    }

    public static void TestSingleThreadPoolWIthException() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                log.debug("程序出现错误，抛出异常，但线程池会开出新的线程执行接下来的任务");
                int i = 1 / 0;
            }
        });
        singleThreadExecutor.execute(() -> {
            log.debug("{}", Thread.currentThread().getName());
        });
        singleThreadExecutor.execute(() -> {
            log.debug("{}", Thread.currentThread().getName());
        });
        singleThreadExecutor.execute(() -> {
            log.debug("{}", Thread.currentThread().getName());
        });
    }

    public static void TestSingleThreadPool() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            int num = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    log.debug("{}", num);
                }
            });
        }
    }

    public static void TestFixedThreadPool() {
        AtomicInteger atomicInteger = new AtomicInteger();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "WorkerThread_" + atomicInteger.getAndIncrement());
            }
        });

        for (int i = 0; i < 5; i++) {
            int num = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    log.debug("{}", num);
                }
            });
        }
    }
}
