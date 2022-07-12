package threadPool.threadPoolExecutor.method;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * 哪个任务先成功执行完毕，此任务执行结果作为最终结果返回，其它任务会被取消
 */
@Slf4j
public class invokeAnyDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService pool = Executors.newFixedThreadPool(1);

        Object o = pool.invokeAny(Arrays.asList(
                () -> {
                    log.debug("task");
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("task end");
                    return "1";
                },
                () -> {
                    log.debug("task");
                    log.debug("task end");
                    return "2";
                },
                () -> {
                    log.debug("task");
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("task end");
                    return "3";
                }
        ),1,TimeUnit.SECONDS);

        log.debug("{}", o);
    }
}
