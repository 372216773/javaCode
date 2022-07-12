package threadPool.forkJoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join 是 JDK 1.7 加入的新的线程池实现，它体现的是一种分治思想(任务合理拆分,再将结果合并,中间使用线程优化)，
 * 适用于能够进行任务拆分的 cpu 密集型运算(充分利用CPU进行计算)
 * <p>
 * Fork/Join 在分治的基础上加入了多线程，可以把每个任务的分解和合并交给不同的线程来完成，进一步提升了运算效率
 * <p>
 * Fork/Join 默认会创建与 cpu 核心数大小相同的线程池
 * fork() 交给一个线程去执行这个任务, join() 获取任务的结果
 * 提交给 Fork/Join 线程池的任务需要继承 RecursiveTask(有返回值) 或 RecursiveAction(没有返回值)
 */
@Slf4j
public class ForkJoinDemo {
    public static void main(String[] args) {
        /**
         * parallelism: 并发度
         */
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        Integer result = forkJoinPool.invoke(new MyTask1(1, 10));

        log.debug("{}", result);
    }
}

/**
 * 1~n 求和
 */
@Slf4j
class MyTask extends RecursiveTask<Integer> {

    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    //任务拆分
    @Override
    protected Integer compute() {
        if (n == 1) {
            log.debug("{} - join({})", Thread.currentThread().getName(), n);
            return 1;
        }

        MyTask myTask = new MyTask(n - 1);
        // fork(): 将这个任务交给一个线程去执行
        myTask.fork();
        log.debug("{} - fork({}) + {}", Thread.currentThread().getName(), n, myTask);

        //join():获取任务的结果
        int result = n + myTask.join();

        log.debug("{} - join({}) + {} = {}", Thread.currentThread().getName(), n, myTask, result);
        return result;
    }
}

/**
 * 二分法
 */
@Slf4j
class MyTask1 extends RecursiveTask<Integer> {
    int begin;
    int end;

    public MyTask1(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" + begin + "," + end + '}';
    }

    @Override
    protected Integer compute() {
        // 5, 5
        if (begin == end) {
            System.out.println("join() " + begin);
            return begin;
        }
        // 4, 5
        if (end - begin == 1) {
            System.out.println("join() " + begin + " + " + end + " = " + (end + begin));
            return end + begin;
        }

        // 1 5
        int mid = (end + begin) / 2; // 3
        MyTask1 t1 = new MyTask1(begin, mid); // 1,3
        t1.fork();
        MyTask1 t2 = new MyTask1(mid + 1, end); // 4,5
        t2.fork();
        System.out.println("fork() " + t1 + " + " + t2 + " = ?");
        int result = t1.join() + t2.join();
        System.out.println("join() " + t1 + " + " + t2 + " = " + result);
        return result;
    }
}