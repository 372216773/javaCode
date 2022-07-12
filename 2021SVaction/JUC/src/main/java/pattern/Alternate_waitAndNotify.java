package pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 交替输出
 * abc
 */
public class Alternate_waitAndNotify {
    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify(5, 1);

        new Thread(() -> {
            waitNotify.print("a", 1, 2);
        }, "t1").start();

        new Thread(() -> {
            waitNotify.print("b", 2, 3);
        }, "t2").start();

        new Thread(() -> {
            waitNotify.print("c", 3, 1);
        }, "t3").start();
    }
}

@Slf4j
class WaitNotify {

    private final int loopNum;
    private int flag;

    public WaitNotify(Integer loopNum, Integer flag) {
        this.loopNum = loopNum;
        this.flag = flag;
    }

    public void print(String str, Integer current, Integer next) {
        synchronized (this) {
            for (int i = 0; i < loopNum; i++) {
                //使用 if 是不对的
                while (flag!=current) {
                    //如果拿到所的线程不是执行的线程就继续进入等待
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                log.debug("{}", str);

                //循环只输出一次，就将标记换到下一个
                flag = next;
                //唤醒所有线程，释放锁
                this.notifyAll();
            }
        }
    }
}
