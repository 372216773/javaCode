package threadPool.threadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduleExecutorService
 * 线程数固定，任务数多于线程数时，会放入无界队列排队(阻塞队列可以放任意数量的任务)。
 * 任务执行完毕，这些线程也不会被释放,用来执行延迟或反复执行的任务。
 */
@Slf4j
public class scheduleExecutorServiceDemo {
    public static void main(String[] args) {
        Demo0();
    }

    /**
     * command: 任务
     * delay: 延时时间
     * unit: 时间单位
     */
    public static void Demo0() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

        log.debug("start");

        pool.schedule(() -> {
            log.debug("task1");
        }, 2, TimeUnit.SECONDS);

        pool.schedule(() -> {
            log.debug("task2");
        }, 1, TimeUnit.SECONDS);
    }

    /**
     * command: 任务
     * delay: 延时时间
     * unit: 时间单位
     * <p>
     * 前一个任务的异常不会影响其他任务的执行
     */
    public static void Demo1() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

        log.debug("start");

        scheduledThreadPool.schedule(() -> {
            log.debug("task1");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 1 / 0;
            log.debug("task1 end");
        }, 1, TimeUnit.SECONDS);

        scheduledThreadPool.schedule(() -> {
            log.debug("task2");
        }, 1, TimeUnit.SECONDS);
    }

    /**
     * scheduleAtFixedRate 方法的使用(以固定周期频率执行任务)
     * <p>
     * command：执行线程
     * initialDelay：初始化延时(任务执行前延时)
     * period：两次开始执行最小间隔时间(隔 period 执行一次)
     * unit：计时单位
     */
    public static void Demo2() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

        log.debug("start");

        scheduledThreadPool.scheduleAtFixedRate(() -> {
            log.debug("task start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task end");
        }, 2, 3, TimeUnit.SECONDS);
    }

    /**
     * scheduleWithFixedDelay 方法的使用(以固定延迟时间进行执行.本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务)
     * <p>
     * command：执行线程
     * initialDelay：初始化延时
     * period：前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间）
     * unit：计时单位
     */
    public static void Demo3() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

        log.debug("start");

        scheduledThreadPool.scheduleWithFixedDelay(() -> {
            log.debug("start task");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task end");
        }, 2, 3, TimeUnit.SECONDS);
    }
}
