package reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件变量
 */
@Slf4j
public class ReentrantLockDemo4 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Thread thread = new Thread(() -> {
            try {
                log.debug("获得锁");
                reentrantLock.lock();


                //进入条件变量中等待
                log.debug("进入条件变量中等待，并释放锁");
                condition.await();

                log.debug("重新获得锁");
                log.debug("执行临界区的代码");
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.debug("释放锁");
                reentrantLock.unlock();
            }
        }, "t1");


        log.debug("开启t1线程");
        thread.start();

        TimeUnit.SECONDS.sleep(2);
        log.debug("main 线程获得锁");
        reentrantLock.lock();
        log.debug("唤醒 condition 里的等待的线程");
        condition.signal();
        log.debug("main线程释放锁");
        reentrantLock.unlock();
    }
}
