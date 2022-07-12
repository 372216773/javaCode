package method;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport park,unpark
 */
@Slf4j
public class parkAndUnPark {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("be parked");
            LockSupport.park();
            log.debug("de resumed");
        }, "t1");

        thread.start();

        TimeUnit.SECONDS.sleep(5);
        log.debug("unpark thread");
        LockSupport.unpark(thread);
    }
}
