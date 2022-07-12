import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * 与 countDownLatch 相比，CyclicBarri可以重用
 */
@Slf4j
public class CyclicBarriDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        method(pool);
    }

    public static void method(ExecutorService pool){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{
            log.debug("finish====================");
        });

        for (int i = 0; i < 5; i++) {
            pool.execute(()->{
                log.debug("start");

                try {
                    TimeUnit.SECONDS.sleep(2);
                    //计数-1，不为0，就会阻塞住
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                log.debug("end");
            });
            pool.execute(()->{
                log.debug("start");

                try {
                    TimeUnit.SECONDS.sleep(2);
                    //计数-1，不为0，就会阻塞住
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                log.debug("end");
            });
        }
    }

    /**
     * countDownLatch 对照组
     * 因为state被减为0后，不会重置，导致后边的state一直为0，没有达到预期的结果
     */
    public static void method1(ExecutorService pool) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < 5; i++) {
            pool.execute(() -> {
                log.debug("start");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                log.debug("end");
            });
            pool.execute(() -> {
                log.debug("start");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                log.debug("end");
            });
            pool.execute(() -> {
                log.debug("start");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                log.debug("end");
            });
            TimeUnit.SECONDS.sleep(1);
            log.debug("自旋等待state=0");
            countDownLatch.await();
            log.debug("state==0,恢复运行=================================");
        }

        pool.shutdown();
    }
}
