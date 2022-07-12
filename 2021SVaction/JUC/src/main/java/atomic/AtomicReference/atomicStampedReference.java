package atomic.AtomicReference;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * atomicStampedReference 用版本号解决 ABA 问题
 * 判断的依据都是靠自己设置的值来判断，每次修改值，版本号都需要自己手动设置不同的值
 * 通过版本号的更新，可以知道被改过多少次
 */
@Slf4j
public class atomicStampedReference {
    private static AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("A", 1);

    public static void main(String[] args) {

        log.debug("start");
        String prev = atomicStampedReference.getReference();
        log.debug("getReference(): {}", prev);

        int stamp = atomicStampedReference.getStamp();
        log.debug("getStamp(): {}",stamp);

        operate();

        //比较两个东西,值和版本号都与主存中的数据相同,才能修改为新的值
        boolean change = atomicStampedReference.compareAndSet(prev, "B", stamp, stamp + 1);
        log.debug("cas was succeed: {}, current Reference: {}, current Stamp: {}", change, atomicStampedReference.getReference(), atomicStampedReference.getStamp());

    }

    public static void operate() {
        Thread thread = new Thread(() -> {
            log.debug("start");
            String prev = atomicStampedReference.getReference();
            log.debug("getReference(): {}", prev);

            int stamp = atomicStampedReference.getStamp();
            log.debug("getStamp(): {}", stamp);

            boolean change = atomicStampedReference.compareAndSet(prev, "B", stamp, stamp + 1);
            log.debug("cas was succeed: {}, current Reference: {}, current Stamp: {}", change, atomicStampedReference.getReference(), atomicStampedReference.getStamp());
            log.debug("end");
        });

        Thread thread1 = new Thread(() -> {
            log.debug("start");
            String prev = atomicStampedReference.getReference();
            log.debug("getReference(): {}", prev);

            int stamp = atomicStampedReference.getStamp();
            log.debug("getStamp(); {}", stamp);

            boolean change = atomicStampedReference.compareAndSet(prev, "A", stamp, stamp + 1);
            log.debug("cas was succeed: {}, current Reference: {}, current Stamp: {}", change, atomicStampedReference.getReference(), atomicStampedReference.getStamp());
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
