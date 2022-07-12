package threadPool.threadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定大小的线程池
 * - 核心线程数：nThreads
 * - 线程工厂：threadFactory
 * 适用于任务量已知, 相对耗时的任务
 */
@Slf4j
public class fixedThreadPoolDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();

        ExecutorService pool = Executors.newFixedThreadPool(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "WorkerThread_" + atomicInteger.getAndIncrement());
            }
        });

        for (int i = 0; i < 10; i++) {
            pool.execute(() -> {
                log.debug("{}", Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
