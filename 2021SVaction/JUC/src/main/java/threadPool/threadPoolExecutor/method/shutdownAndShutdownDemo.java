package threadPool.threadPoolExecutor.method;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * shutdown():
 * 线程池状态变为 SHUTDOWN
 * 不会接收新任务
 * 但已提交任务会执行完
 * 此方法不会阻塞调用线程的执行(如果主线程调用了 shutdown(), 接下来还有其他代码, 还会运行这些代码)
 *
 * shutdownNow():
 * 将线程池的状态改为 STOP
 * 不再接受新任务
 * 也不会再执行阻塞队列中的任务，会将阻塞队列中未执行的任务返回给调用者
 * 此方法不会阻塞调用线程的执行
 *
 */

@Slf4j
public class shutdownAndShutdownDemo {
    public static void main(String[] args) throws InterruptedException {
        shutdownNowTest();
    }

    public static void shutdownTest() {
        ExecutorService pool = Executors.newFixedThreadPool(2);


        Future<String> task0 = pool.submit(() -> {
            log.debug("task0 running");
            TimeUnit.SECONDS.sleep(1);
            log.debug("task0 finish");
            return "0";
        });
        Future<String> task1 = pool.submit(() -> {
            log.debug("task1 running");
            TimeUnit.SECONDS.sleep(1);
            log.debug("task1 finish");
            return "1";
        });
        Future<String> task2 = pool.submit(() -> {
            log.debug("task2 running");
            TimeUnit.SECONDS.sleep(1);
            log.debug("task2 finish");
            return "2";
        });

        pool.shutdown();

        /*
         * 会报错 RejectedExecutionException，不允许再添加任务
         */
        /*Future<String> task3 = pool.submit(() -> {
            log.debug("task3 running");
            TimeUnit.SECONDS.sleep(1);
            log.debug("task3 finish");
            return "3";
        });*/

        log.debug("other......");
    }

    public static void shutdownNowTest() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);


        Future<String> task0 = pool.submit(() -> {
            log.debug("task0 running");
            TimeUnit.SECONDS.sleep(1);
            log.debug("task0 finish");
            return "0";
        });
        Future<String> task1 = pool.submit(() -> {
            log.debug("task1 running");
            TimeUnit.SECONDS.sleep(2);
            log.debug("task1 finish");
            return "1";
        });
        Future<String> task2 = pool.submit(() -> {
            log.debug("task2 running");
            TimeUnit.SECONDS.sleep(1);
            log.debug("task2 finish");
            return "2";
        });

        TimeUnit.SECONDS.sleep(1);

        List<Runnable> runnableList = pool.shutdownNow();

        log.debug("{}",runnableList);

        /* 会报错 RejectedExecutionException ,不允许再添加新任务
            pool.submit(() -> {
            log.debug("task3 running");
            TimeUnit.SECONDS.sleep(1);
            log.debug("task3 finish");
            return "3";
        });*/

        // 不会阻塞调用线程的执行
        log.debug("other......");
    }
}
