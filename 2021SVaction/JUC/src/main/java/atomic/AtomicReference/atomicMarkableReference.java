package atomic.AtomicReference;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * atomicMarkableReference 是 atomicStampedReference的简化版
 * 不需要知道改过多少次，只需要知道该没改过
 */
@Slf4j
public class atomicMarkableReference {

    private static AtomicMarkableReference<String> atomicMarkableReference = new AtomicMarkableReference<>("A", true);

    public static void main(String[] args) {

        log.debug("start");

        String prev = atomicMarkableReference.getReference();
        log.debug("getReference(): {}", prev);

        boolean marked = atomicMarkableReference.isMarked();
        log.debug("isMarked(): {}", marked);

        operate();

        boolean change = atomicMarkableReference.compareAndSet(prev, "B", true, false);
        log.debug("cas was succeed: {}, current Reference: {}, current Marked: {}", change, atomicMarkableReference.getReference(), atomicMarkableReference.isMarked());
    }

    public static void operate() {
        Thread thread = new Thread(() -> {
            log.debug("start");

            String prev = atomicMarkableReference.getReference();
            atomicMarkableReference.isMarked();

            boolean change = atomicMarkableReference.compareAndSet(prev, "B", true, false);
            log.debug("cas was succeed: {}, current Reference: {}, current Marked: {}", change, atomicMarkableReference.getReference(), atomicMarkableReference.isMarked());
            log.debug("end");

        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}