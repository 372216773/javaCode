package method;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 使用 interrupt方法打断正常运行的线程，只是更改该线程的被打断标记，并不会使线程停止
 */
@Slf4j
public class interrupt_common {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("{} start",Thread.currentThread().getName());
                while (true) {
                    boolean interrupted = Thread.currentThread().isInterrupted();

                    //对被打断标记进行判断
                    if (interrupted) {
                        log.debug("t1 isInterrupted is {}",true);
                        log.debug("exit");
                        //使其退出循环，结束该线程
                        break;
                    }
                }
            }
        }, "t1");

        thread.start();

        TimeUnit.SECONDS.sleep(3);
        thread.interrupt();
    }
}
