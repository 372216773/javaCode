package reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *可打断(中断 获取锁时被阻塞的线程)
 */
@Slf4j
public class ReentrantLockDemo2 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread thread = new Thread(() -> {
            //获取锁
            try {
                log.debug("尝试获得锁");
                reentrantLock.lockInterruptibly();
            } catch (InterruptedException e) {
                log.debug("没有获得锁，被main线程中断了等待，抛出异常");
            }
        },"t1");


        //main线程先拿到锁，t1线程就会阻塞住，一直等待锁的释放
        log.debug("main线程先拿到了锁，t1线程就会阻塞住");
        reentrantLock.lock();

        log.debug("开始t1线程");
        thread.start();

        TimeUnit.SECONDS.sleep(2);
        //中断t1线程，让她别再等待
        log.debug("中断t1线程，让她别再等待");
        thread.interrupt();
    }
}
