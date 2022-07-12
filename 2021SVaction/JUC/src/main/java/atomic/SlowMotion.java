package atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 慢动作分析
 */
@Slf4j
public class SlowMotion {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger balance = new AtomicInteger(10000);
        int mainPrev = balance.get();

        log.debug("try get balance: {}", mainPrev);

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int prev = balance.get();

            log.debug("try set 9000 to balance");
            boolean success = balance.compareAndSet(prev, 9000);
            log.debug("cas was succeed: {}",success);

            log.debug("current balance: {}",balance);
        }, "t1").start();

        TimeUnit.SECONDS.sleep(2);

        log.debug("try set 8000 to balance");
        boolean isSuccess = balance.compareAndSet(mainPrev, 8000);

        log.debug("cas was succeed ? {}", isSuccess);

        if(!isSuccess){
            mainPrev = balance.get();
            log.debug("current balance: {}",mainPrev);

            log.debug("try set 8000 to balance again");
            isSuccess = balance.compareAndSet(mainPrev, 8000);
            log.debug("is success ? {}", isSuccess);
            log.debug("current balance: {}",balance);
        }
    }

}

