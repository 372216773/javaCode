package atomic.AtomicReference;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * ABA问题
 */
@Slf4j
public class matterABA {
    public static AtomicReference<String> atomicReference = new AtomicReference<>("A");

    public static void main(String[] args) throws InterruptedException {

        log.debug("start");

        String prev = atomicReference.get();
        log.debug("get(): {}", prev);

        //A-->B-->A
        operate();

        log.debug("cas A-->B");
        boolean change = atomicReference.compareAndSet(prev, "B");
        log.debug("cas A-->B {}",change);
        String current = atomicReference.get();
        log.debug("current: {}", current);
    }

    public static void operate() {
        Thread thread = new Thread(() -> {
            log.debug("start");
            String prev = atomicReference.get();
            boolean change = atomicReference.compareAndSet(prev, "B");
            log.debug("cas A-->B {}",change);
            log.debug("end");
        });

        Thread thread1 = new Thread(() -> {
            log.debug("start");
            String prev = atomicReference.get();
            boolean change = atomicReference.compareAndSet(prev, "A");
            log.debug("cas B-->A {}",change);
            log.debug("end");
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
