package method;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class waitAndNotify {
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                log.debug("获得锁");

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                log.debug("被唤醒了");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("执行完毕，释放锁");
            }
        },"t1");

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                log.debug("获得锁");

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                log.debug("被唤醒了");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("执行完毕，释放锁");
            }
        }, "t2");

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                log.debug("获得锁");

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                log.debug("被唤醒了");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("执行完毕，释放锁");
            }
        }, "t3");

        thread.start();
        thread1.start();
        thread2.start();

        TimeUnit.SECONDS.sleep(4);
        synchronized (lock) {
            //要使用这些方法，必须先获得锁，即进入synchronized
            log.debug("拿到了锁");

            //随即唤醒一个线程
            lock.notify();

            log.debug("正在随机唤醒waitSet中的某个线程......");
            TimeUnit.SECONDS.sleep(2);

            log.debug("同步代码块已经执行完毕，释放锁，唤醒线程");
        }
    }
}
