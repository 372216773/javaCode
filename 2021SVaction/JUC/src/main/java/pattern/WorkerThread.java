package pattern;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 *
 */
@Slf4j
public class WorkerThread {
    static final List<String> MENU = Arrays.asList("地三鲜", "宫保鸡丁", "辣子鸡", "烤鸡翅");

    static Random RANDOM = new Random();

    static String cooking() {
        return MENU.get(RANDOM.nextInt(MENU.size()));
    }

    public static void main(String[] args) {
        Demo1();
    }

    /**
     * 两种工作: 1. 点餐+上菜; 2. 烹饪
     * 一个人同时只能做一件事
     * <p>
     * 现在有两个线程，如果有两个客人，两个线程同时进行点餐的工作，都在等着上菜，就会阻塞住，没人去烹饪
     */
    public static void Demo() {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.execute(() -> {
            log.debug("处理点餐");

            Future<String> dish = pool.submit(() -> {
                log.debug("cooking");

                return cooking();
            });

            log.debug("调用了 get(), 会一直等待上菜");
            try {
                log.debug("上菜: {}", dish.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        pool.execute(() -> {
            log.debug("处理点餐");

            Future<String> dish = pool.submit(() -> {
                log.debug("cooking");
                TimeUnit.SECONDS.sleep(2);
                return cooking();
            });

            log.debug("调用了 get(), 会一直等待上菜");

            try {
                log.debug("上菜: {}", dish.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 解决方法: 不同的任务类型使用不同的线程池
     * 分为员工A类型: 只做点餐+上菜的工作; 员工B类型: 只做烹饪工作
     * 针对于客人的数量进行线程的增加，不会出现上边的死等情况
     */
    public static void Demo1() {
        ExecutorService staffA = Executors.newFixedThreadPool(2);
        ExecutorService staffB = Executors.newFixedThreadPool(2);

        staffA.execute(() -> {
            log.debug("处理点餐");

            Future<String> dish = staffB.submit(() -> {
                log.debug("cooking");

                return cooking();
            });

            log.debug("调用了 get(), 会一直等待上菜");
            try {
                log.debug("上菜: {}", dish.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        staffA.execute(() -> {
            log.debug("处理点餐");

            Future<String> dish = staffB.submit(() -> {
                log.debug("cooking");

                return cooking();
            });

            log.debug("调用了 get(), 会一直等待上菜");
            try {
                log.debug("上菜: {}", dish.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
