package principle;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * AQS 原理: 全称是 AbstractQueuedSynchronizer(抽象队列同步器)，是阻塞式锁和相关的同步器工具的框架(其他的同步器工具都是基于他的, 都是他的子类)
 */
@Slf4j
public class AQS_principle {
    public static void main(String[] args) {
        MyLock myLock = new MyLock();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                myLock.lock();
                log.debug("get Lock");

                log.debug("running");

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                myLock.unlock();
                log.debug("unLock");
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                myLock.lock();
                log.debug("get Lock");

                log.debug("running");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                myLock.unlock();
                log.debug("unLock");
            }
        };

        new Thread(runnable).start();
        new Thread(runnable1).start();
    }
}

/**
 * 1.作为锁的类要实现Lock接口(作为锁的壳子)-->锁的相关方法
 * <p>
 * 2.使用到AQS同步器类(锁的内核)-->是阻塞式锁的框架
 */
class MyLock implements Lock {

    /**
     * 使用 AbstractQueuedSynchronizer 自定义一个同步器来实现一个自定义锁
     * <p>
     * 自定义同步器, 实现独占锁(只有一个线程能够访问资源)
     * <p>
     * arg: 无意义
     */
    class MySync extends AbstractQueuedSynchronizer {

        /**
         * 尝试获取锁
         * <p>
         * 成功返回 true; 失败返回 false
         */
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                //加上了锁， 0--> 1,并设置 owner 为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 尝试释放锁
         * <p>
         * 释放锁: 设置 owner 为 null，并且 state 设置为 0
         */
        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            //0: 表示解锁，volatile加写屏障，避免重排序
            setState(0);
            return true;
        }

        /**
         * 是否持有独占锁
         * <p>
         * state(): 0 or 1
         */
        @Override
        protected boolean tryReleaseShared(int arg) {
            return getState() == 1;
        }

        /**
         * 获取条件变量
         */
        public Condition newCondition(int arg) {
            return new ConditionObject();
        }
    }

    private MySync sync = new MySync();

    /**
     * 加锁
     * <p>
     * 不成功会放入等待队列进行等待
     */
    @Override
    public void lock() {

        sync.acquire(1);
    }

    /**
     * 加锁
     * <p>
     * 可打断
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    /**
     * 尝试加锁，只会尝试一次
     */
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    /**
     * 带超时的加锁
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {
        //调用了修改tryRelease,state为0,owner为null,还会唤醒正在阻塞的线程
        sync.release(1);
    }

    /**
     * 获取条件变量
     */
    @Override
    public Condition newCondition() {
        return sync.newCondition(1);
    }
}
