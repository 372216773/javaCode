package Synchronized;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程1：对静态属性count+500000
 * 线程2：对静态属性count-500000
 * 希望的结果是 count=0
 * 使用synchronized解决
 */
@Slf4j
public class Demo {

    //只存在一份，所以设置为 static
    private static int count;

    //类属性，在类加载时初始化
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 500000; i++) {
                synchronized (object) {
                    count++;
                }
            }
        }, "t1");

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500000; i++) {
                synchronized (object) {
                    count--;
                }
            }
        }, "t2");

        thread.start();
        thread1.start();

        //避免两个线程还未开始操作，主线程先结束了
        thread.join();
        thread1.join();

        log.debug("count {}", count);

        //没加对象锁的情况

        /*test test = new test();

        test.testDemo();*/

    }
}

@Slf4j
/**
 * 没加对象锁的情况
 */
class test{

    private static int count;

    public void testDemo() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 500000; i++) {
                count++;
            }
        }, "t1");

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500000; i++) {
                count--;
            }
        }, "t1");

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();

        log.debug("count {}",count);
    }
}
