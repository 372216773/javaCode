package reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入
 */
@Slf4j
public class ReentrantLockDemo1 {
    public static final ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        m1();
    }

    public static void m1() {
        //加锁
        reentrantLock.lock();
        m2();

        try {
            log.debug("临界区代码1");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //解锁
            reentrantLock.unlock();
        }
    }

    private static void m2() {

        //加锁
        reentrantLock.lock();

        try {
            log.debug("临界区代码2");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //解锁
            reentrantLock.unlock();
        }
    }
}
