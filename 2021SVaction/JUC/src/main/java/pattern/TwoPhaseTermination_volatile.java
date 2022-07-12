package pattern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 两阶段终止模式，加入 volatile + 犹豫模式(用一个标记来判断该任务是否已经被执行过了)
 * <p>
 * 通过 interrupt 进行中断，来改变flag的值；通过判断flag的值来对中断作出反应
 */
public class TwoPhaseTermination_volatile {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination1 twoPhaseTermination1 = new TwoPhaseTermination1();

        twoPhaseTermination1.start();
        twoPhaseTermination1.start();

        TimeUnit.SECONDS.sleep(6);

        twoPhaseTermination1.stop();
    }
}

@Slf4j
class TwoPhaseTermination1 {

    private Thread monitor;
    private boolean flag = false;
    private boolean started=false;

    public void start() {
        monitor = new Thread(() -> {
            synchronized (this) {

                if (started)
                    return;

                //一个线程创建过后，更改标识，使别的线程不会多次创建
                started=true;
            }

            while (true) {
                if (flag) {
                    log.debug("对于被中断的线程，我的操作是退出");
                    break;
                }

                log.debug("执行临界代码块");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    log.debug("线程处于 sleep 状态被打断，isInterrupted 设置为 false，抛出异常");
                }
            }
        });

        monitor.start();
    }

    public void stop() {
        flag = true;
        //中断monitor线程
        monitor.interrupt();
    }
}
