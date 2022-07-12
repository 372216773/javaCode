package pattern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * 交替输出
 */
public class alternateParkAndUnPark {
    private static Thread thread;
    private static Thread thread1;
    private static Thread thread2;
    public static void main(String[] args) {
        ParkUnPark parkUnPark = new ParkUnPark(5);

        thread = new Thread(()->{
            parkUnPark.print("a",thread1);
        });

        thread1 = new Thread(()->{
            parkUnPark.print("b",thread2);
        });

        thread2 = new Thread(()->{
            parkUnPark.print("c",thread);
        });

        thread.start();
        thread1.start();
        thread2.start();

        parkUnPark.start(thread);
    }
}

@Slf4j
class ParkUnPark {

    private final int loopNum;

    public ParkUnPark(int loopNum) {
        this.loopNum = loopNum;
    }

    public void print(String str,Thread next) {
        for (int i = 0; i < loopNum; i++) {
            //暂停运行线程
            LockSupport.park();

            log.debug("{}",str);

            //唤醒下一线程
            LockSupport.unpark(next);
        }
    }

    public void start(Thread start) {
        LockSupport.unpark(start);
    }
}
