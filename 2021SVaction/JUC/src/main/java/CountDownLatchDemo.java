import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 用来进行线程同步协作，等待所有线程完成倒计时，才恢复运行。
 */
@Slf4j
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(3);

        pool.execute(()->{
            log.debug("begin");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("running");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("通过cas将state-1，进入阻塞队列中阻塞");
            //通过cas将state-1，进入阻塞队列中阻塞
            countDownLatch.countDown();

            log.debug("state==0。恢复运行");
        });
        pool.execute(()->{
            log.debug("begin");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("running");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.debug("通过cas将state-1，进入阻塞队列中阻塞");
            //通过cas将state-1，进入阻塞队列中阻塞
            countDownLatch.countDown();

            log.debug("state==0。恢复运行");
        });
        pool.execute(()->{
            log.debug("begin");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("running");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("通过cas将state-1，进入阻塞队列中阻塞");
            //通过cas将state-1，进入阻塞队列中阻塞
            countDownLatch.countDown();

            log.debug("state==0。恢复运行");
        });


        TimeUnit.SECONDS.sleep(3);
        log.debug("调用await(),会进入自旋，等待state=0");
        countDownLatch.await();
        log.debug("state==0，恢复运行");

        pool.shutdown();
    }
}
