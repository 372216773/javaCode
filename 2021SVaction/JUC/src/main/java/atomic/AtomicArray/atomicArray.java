package atomic.AtomicArray;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 原子数组
 */
@Slf4j
public class atomicArray {
    public static void main(String[] args) {
        demo(
                () -> new int[10],
                (array) -> array.length,
                (array, index) -> array[index]++,
                (array) -> log.debug("{}", Arrays.toString(array))
        );

        demo(
                ()->new AtomicIntegerArray(10),
                (array)->array.length(),
                (array,index)->array.getAndIncrement(index),
                (array)->log.debug("{}",array.toString())
        );
    }

    public static <T> void demo(//对象
                                Supplier<T> arraySupplier,
                                Function<T, Integer> lengthFunction,
                                BiConsumer<T, Integer> putConsumer,
                                Consumer<T> printConsumer
    ) {
        ArrayList<Thread> threadList = new ArrayList<>();

        T array = arraySupplier.get();
        Integer length = lengthFunction.apply(array);

        for (int i = 0; i < length; i++) {
            //为线程列表添加元素
            threadList.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    //对数组元素的操作会出现并发问题
                    putConsumer.accept(array, j % length);
                }
            }));
        }
        threadList.forEach(Thread::start);

        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        printConsumer.accept(array);
    }
}
