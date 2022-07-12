import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 支持重入的读写锁
 *
 * 之前学习的锁都是独占锁，同一时刻只允许一个线程访问数据
 *
 * 特点：当读操作远远高于写操作时，这时候使用读写锁让 读-读 可以并发，提高性能。读-写，写-写都是相互互斥的！
 */
@Slf4j
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {

        lockDemo lockDemo = new lockDemo();
        method3(lockDemo);

    }

    /**
     * 读读互斥
     * @param demo
     */
    public static void method1(lockDemo demo){
        new Thread(()->{
            demo.write();
        }).start();
        new Thread(()->{
            demo.write();
        }).start();
    }

    /**
     * 读写互斥
     * @param demo
     */
    public static void method2(lockDemo demo) {
        new Thread(()->{
            demo.read();
        }).start();

        new Thread(()->{
            demo.read();
        }).start();
    }

    public static void method3(lockDemo demo) {
        new Thread(()->{
            demo.read();
        }).start();
        new Thread(()->{
            demo.write();
        }).start();
    }

    static class lockDemo {
        private Object data;

        private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

        public Object read() {
            readLock.lock();
            log.debug("获取到读锁");

            try {
                log.debug("读数据");

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return data;

            } finally {
                readLock.unlock();
                log.debug("释放读锁");
            }
        }

        public void write() {
            writeLock.lock();
            log.debug("获取写锁");

            try {
                log.debug("写数据");

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                writeLock.unlock();
                log.debug("释放写锁");
            }
        }
    }
}
