package pattern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替输出
 */
@Slf4j
public class Alternate_awaitAndSignal {
    public static void main(String[] args) {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition condition = awaitSignal.getCondition();
        Condition condition1 = awaitSignal.getCondition();
        Condition condition2 = awaitSignal.getCondition();

        new Thread(() -> {
            awaitSignal.print("a", condition, condition1);
        }).start();

        new Thread(() -> {
            awaitSignal.print("b", condition1, condition2);
        }).start();

        new Thread(() -> {
            awaitSignal.print("c", condition2, condition);
        }).start();

        awaitSignal.start(condition);
    }
}

@Slf4j
class AwaitSignal {

    private final int loopNum;
    private final ReentrantLock reentrantLock = new ReentrantLock();

    public AwaitSignal(int loopNum) {
        this.loopNum = loopNum;
    }

    public void print(String str, Condition current, Condition next) {
        for (int i = 0; i < loopNum; i++) {
            reentrantLock.lock();

            try {
                try {
                    current.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                log.debug("{}", str);

                //唤醒下一个条件变量中的线程
                next.signal();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void start(Condition start) {
        reentrantLock.lock();
        //唤醒这个条件变量中等待的线程
        try {
            start.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    public Condition getCondition() {
        return reentrantLock.newCondition();
    }
}