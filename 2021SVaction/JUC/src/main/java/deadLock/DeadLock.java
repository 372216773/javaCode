package deadLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 死锁情况
 */
@Slf4j
public class DeadLock {
    private static final Object A = new Object();
    private static final Object B = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized(A) {
                log.debug("获得 A 的对象锁");

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized(B) {
                    log.debug("获得 B 的对象锁");
                }
            }
        },"t1").start();

        new Thread(()->{
            synchronized(B) {
                log.debug("获得 B 的对象锁");

                synchronized(A) {
                    log.debug("获得 A 的对象锁");
                }
            }
        },"t2").start();
    }
}
