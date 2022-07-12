package threadPool.threadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 带缓冲的线程池
 * 核心线程为0，最大线程数为 Integer.MAX_VALUE，所有创建的线程都是救急线程，空闲时生存时间为60秒,在生存时间内线程可以进行复用
 * 整个线程池表现为线程数会根据任务量不断增长, 没有上限, 当任务执行完后, 空闲一分钟后释放线程
 * 适合任务数比较密集, 但每个任务执行时间较短的情况
 */
@Slf4j
public class cachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    log.debug("{}", Thread.currentThread().getName());
                }
            });
        }
    }
}