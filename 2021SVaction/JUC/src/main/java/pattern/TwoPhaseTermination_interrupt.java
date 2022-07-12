package pattern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 两阶段终止模式
 * 当我们在执行线程一时，想要终止线程二，这是就需要使用 interrupt 方法来优雅的停止线程二，
 * 即不论线程二是处于正常运行还是阻塞状态，只要调用线程二的方法都能使他停止
 * 通过判断 isInterrupted 来对中断作出反应
 */
@Slf4j
public class TwoPhaseTermination_interrupt {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();

        twoPhaseTermination.start();

        TimeUnit.SECONDS.sleep(10);
        twoPhaseTermination.stop();
    }
}

@Slf4j
class TwoPhaseTermination {
    private Thread monitor;

    public void start () {
        monitor=new Thread(new Runnable() {
            @Override
            public void run() {

                log.debug("t1 start");
                while (true) {
                    boolean interrupted = Thread.currentThread().isInterrupted();
                    if (interrupted) {
                        log.debug("isInterrupted: {}",true);
                        log.debug("exit this thread");
                        break;
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        log.debug("is working");
                    } catch (InterruptedException e) {
                        //如果线程处于阻塞状态被打断就抛出异常，并将 interrupted 设置为 false
                        //log.debug("isInterrupted: {}",Thread.currentThread().isInterrupted());
                        //此时线程处于正常状态，打断处于正常状态的线程，使 interrupted 变为 true，进入统一处理的代码块中
                        Thread.currentThread().interrupt();
                    }
                }
            }
        },"t1");

        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}
