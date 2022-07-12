package atomic.AtomicAccumulator;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 累加器性能比较
 */
@Slf4j
public class longAdder {
    public static void main(String[] args) {


        demo(
                () -> new AtomicLong(0),
                (atomicLong) -> atomicLong.getAndIncrement()
        );

        log.debug("");


        demo(
                () -> new LongAdder(),
                (longAdder) -> longAdder.increment()
        );

    }

    public static <T> void demo(
            Supplier<T> addrSupplier,
            Consumer<T> action
    ) {
        T atomicNumber = addrSupplier.get();

        ArrayList<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    action.accept(atomicNumber);
                }
            }));
        }

        long start = System.nanoTime();

        threadList.forEach(thread -> {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();

        log.debug("current: {} cost: {}", atomicNumber, (end - start) / 100_000);
    }
}
