package threadPool.threadPoolExecutor.method;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * invokeAll: 提交 tasks 中所有任务
 *
 * 带超时时间的任务获取，在规定时间内没有执行完的任务不会再执行，抛出错误
 */
@Slf4j
public class invokeAllDemo {
    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    public static void test0() throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        List<Future<String>> futures = fixedThreadPool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("task");
                    TimeUnit.SECONDS.sleep(1);
                    return "1";
                },
                () -> {
                    log.debug("task");
                    TimeUnit.SECONDS.sleep(1);
                    return "2";
                },
                () -> {
                    log.debug("task");
                    TimeUnit.SECONDS.sleep(1);
                    return "3";
                }
        ));

        futures.forEach(future -> {
            try {
                log.debug("{}", future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    public static void test1() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        List<Future<String>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("task start");
                    TimeUnit.SECONDS.sleep(2);
                    return "1";
                },
                () -> {
                    log.debug("task start");
                    TimeUnit.SECONDS.sleep(2);
                    return "2";
                },
                () -> {
                    log.debug("task start");
                    TimeUnit.SECONDS.sleep(2);
                    return "3";
                },
                () -> {
                    log.debug("task start");
                    TimeUnit.SECONDS.sleep(2);
                    return "4";
                }
        ), 2, TimeUnit.SECONDS);

        futures.forEach(future -> {
            try {
                log.debug("{}", future.get());
            } catch (InterruptedException | ExecutionException e) {
                log.debug("error");
            }
        });
    }
}
