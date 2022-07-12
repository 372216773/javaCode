package pattern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 同步模式之顺序控制-wait/notify
 */
@Slf4j
public class SyncMode_waitAndNotify {
    public static void main(String[] args) {
        Object lock = new Object();
        Boolean flag = false;
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                log.debug("获得锁");
                try {
                    log.debug("进入waitSet中进行等待");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                log.debug("被唤醒，重新获得锁");

                log.debug("执行临界区代码");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("执行结束");
            }
        }, "t1");

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                log.debug("获得锁");

                log.debug("执行临界区代码");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notify();
                log.debug("执行完代码，释放锁，唤醒等待的线程");

            }
        }, "t2");

        //不管谁先启动，临界区代码的执行都是先t2再t1
        thread.start();
        thread1.start();

    }
}
