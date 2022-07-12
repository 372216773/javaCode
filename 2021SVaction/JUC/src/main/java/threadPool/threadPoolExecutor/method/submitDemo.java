package threadPool.threadPoolExecutor.method;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * submit(): 返回有结果的任务
 * get(): 等待任务的结果返回，获取返回结果
 */
@Slf4j
public class submitDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
    }

    public static void test0() throws ExecutionException, InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        Future<String> future = fixedThreadPool.submit(() -> {
            log.debug("task");
            TimeUnit.SECONDS.sleep(2);
            return "hello";
        });

        //调用 get() 会阻塞住，直到submit返回值
        log.debug("{}", future.get());
    }

    public static void test1() throws ExecutionException, InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        Future<Boolean> future = fixedThreadPool.submit(() -> {
            try {
                int i = 1 / 0;
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                log.debug("error");
            }
            return false;
        });

        log.debug("{}", future.get());
    }
}
