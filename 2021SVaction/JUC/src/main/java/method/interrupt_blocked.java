package method;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * main 线程调用 t1 线程的 interrupt()，更改 t1 线程的被打断标记，t1 线程对被打断标记进行判断做出相应的操作
 * 使用 interrupt 方法打断正在睡眠的线程，这时 sleep 方法会抛出 InterruptedException, 打断标志会设置为 false
 */
@Slf4j
public class interrupt_blocked {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("t1 isInterrupted status: {}", Thread.currentThread().isInterrupted());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    log.debug("sleep was interrupted");
                    log.debug("打断状态为 {}", Thread.currentThread().isInterrupted());
                }
            }
        }, "t1");

        thread.start();

        TimeUnit.SECONDS.sleep(1);
        log.debug("interrupt");
        thread.interrupt();


    }
}
