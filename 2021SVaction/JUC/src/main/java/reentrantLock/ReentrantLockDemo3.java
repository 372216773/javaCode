package reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁超时
 */
@Slf4j
public class ReentrantLockDemo3 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread thread = new Thread(() -> {
            boolean tryLock = reentrantLock.tryLock();

            if (!tryLock) {
                log.debug("获取锁失败，不会等待");
                log.debug("我对于获取锁失败而进行的操作是直接退出");
                return;
            }

            try {
                log.debug("临界区代码");
            } finally {
                reentrantLock.unlock();
            }


        }, "t1");

        Thread thread1 = new Thread(() -> {
            try {
                if (!reentrantLock.tryLock(3, TimeUnit.SECONDS)) {
                    log.debug("在规定时间内还没有获取锁");
                    log.debug("我对于获取锁失败而进行的操作是直接退出");
                    return;
                }
            } catch (InterruptedException e) {
                log.debug("在尝试获取锁的过程中，被其他线程中断了，抛出中断异常");
            }

            try {
                log.debug("执行临界区代码");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放锁
                log.debug("释放锁");
                reentrantLock.unlock();
            }
        }, "t2");

        //main先拿到锁
        /*log.debug("拿到锁");
        reentrantLock.lock();
        log.debug("开启线程");
        thread1.start();*/

        //thread线程先拿到锁
        thread1.start();
        log.debug("thread拿到锁");

    }
}
