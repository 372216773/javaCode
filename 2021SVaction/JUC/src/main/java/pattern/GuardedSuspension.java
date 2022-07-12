package pattern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 保护性暂停,即Guarded Suspension,用在一个线程等待另一个线程的执行结果,一对一
 */
@Slf4j
public class GuardedSuspension {
    private static final GuardedObject guardedObject = new GuardedObject();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (guardedObject) {
                log.debug("获得锁");
                //设置超时时间
                Object response = guardedObject.get(4000);

                log.debug(String.valueOf(response));
            }
        }, "client").start();

        new Thread(() -> {
            Object o = "Hello";

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //只需锁住需要同步的代码块
            synchronized (guardedObject) {
                log.debug("获得锁");
                guardedObject.set(o);
            }
        }, "server").start();
    }
}

@Slf4j
class GuardedObject {

    private static Object response;

    public Object get(long timeout) {

        //开始时间
        long begin = System.currentTimeMillis();
        //经过时间
        long passTime = 0;

        //如果返回结果为空就一直等待，避免虚假唤醒
        //没有接收到数据时就一直循环。wait时间到了，出循环  1:超时时间到了 2:数据不为空了
        while (response==null) {
            //还需等待的时间
            long waitTime = timeout - passTime;

            //等待时间到了
            if (waitTime <= 0) {
                log.debug("Timeout!!!");
                break;
            }

            try {
                log.debug("释放锁，进入waiting状态，等待被notify");
                this.wait(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            passTime = System.currentTimeMillis() - begin;
        }

        return response;
    }

    public void set(Object o) {
        response = o;
        //这里使用同步，防止在传输过程中就进行获取数据
        synchronized (this) {
            //唤醒等待的线程
            this.notifyAll();
        }
    }

}
