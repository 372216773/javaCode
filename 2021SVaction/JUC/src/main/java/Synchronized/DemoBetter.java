package Synchronized;

import lombok.extern.slf4j.Slf4j;

/**
 * 对使用对象锁的操作进行优化
 */
@Slf4j
public class DemoBetter {

    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                room.increment();
            }
        }, "t1");

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                room.decrement();
            }
        }, "t2");

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();

        log.debug("count: {}", room.getCount());
    }
}

//将互斥的逻辑封装在类内部,对外只需要调用
@Slf4j
class Room {
    public static int count;

    public void increment() {
        //锁住的是当前对象
        synchronized (this) {
            count++;
        }
    }

    public void decrement() {
        synchronized (this) {
            count--;
        }
    }

    public int getCount() {
        //避免读到中间值,确保读到的是操作执行完后的数据
        synchronized (this) {
            return count;
        }
    }
}

    
