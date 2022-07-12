import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 控制最大访问线程数
 */
@Slf4j
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    //获取许可，可访问线程数-1
                    semaphore.acquire();
                    log.debug("获取许可，可访问线程数-1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                log.debug("running...");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("执行完毕，释放许可，可访问线程数+1");
                semaphore.release();

            }).start();
        }
    }
}
