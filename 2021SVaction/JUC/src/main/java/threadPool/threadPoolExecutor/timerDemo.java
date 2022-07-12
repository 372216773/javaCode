package threadPool.threadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Timer
 * 在『任务调度线程池』功能加入之前，可以使用 java.util.Timer 来实现定时功能，Timer 的优点在于简单易用，
 * 但由于所有任务都是由同一个线程来调度，因此所有任务都是串行执行的，同一时间只能有一个任务在执行，前一个任务的延迟或异常都将会影响到之后的任务。
 */
@Slf4j
public class timerDemo {
    public static void main(String[] args) {
        Demo2();
    }

    public static void Demo1() {
        Timer timer = new Timer();

        TimerTask task0 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task0");

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task1");
            }
        };

        log.debug("start......");
        timer.schedule(task0,1000);
        timer.schedule(task1,1000);
    }

    public static void Demo2() {
        Timer timer = new Timer();

        TimerTask task0 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task0");
                int i=1/0;
            }
        };

        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task1");
            }
        };

        timer.schedule(task0,1000);
        timer.schedule(task1,1000);
    }
}
