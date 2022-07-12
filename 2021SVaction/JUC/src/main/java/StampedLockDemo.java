import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock 通过尝试一次乐观读 tryOptimisticRead（）对于读性能进一步提升
 * 如果发现stamp不变，证明在此过程中没有没有进行写操作，就不必进行加锁解锁操作
 */
@Slf4j
public class StampedLockDemo {
    public static void main(String[] args) {

    }

    static class Demo {
        private Object data = 1;

        private StampedLock lock = new StampedLock();

        public Object read() {
            long stamp = lock.tryOptimisticRead();
            log.debug("尝试一次乐观读, stamp={}", stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.debug("读到的数据为 {}", data);

            log.debug("验戳");
            if (lock.validate(stamp)) {
                log.debug("此时 stamp 不变，说明此时没有写操作");
                return data;
            }

            try {
                log.debug("stamp 值被改变，使用读锁");
                stamp = lock.readLock();

                log.debug("读数据");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return data;
            } finally {
                lock.unlockRead(stamp);
                log.debug("释放读锁");
            }
        }

        public void write(Object data) {
            long stamp = lock.writeLock();
            log.debug("获取到写锁");

            try {
                log.debug("写数据");
                this.data = data;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlockWrite(stamp);
            }
        }

    }
}
