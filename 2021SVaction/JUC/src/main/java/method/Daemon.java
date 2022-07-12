package method;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程
 */
@Slf4j
public class Daemon {
    public static void main(String[] args) throws InterruptedException {

        log.debug("start");

        Thread thread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("start");
                TimeUnit.SECONDS.sleep(4);

                log.debug("end");
            }
        }, "t1");

        thread.setDaemon(true);
        thread.start();

        TimeUnit.SECONDS.sleep(2);
        //2s后main线程结束，但是此时t1线程还未结束，由于t1线程是守护线程，所以在其他线程结束后，也会结束
        log.debug("end");
    }
}
