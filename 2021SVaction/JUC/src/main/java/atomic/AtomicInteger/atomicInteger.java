package atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

/**
 * AtomicInteger 基础操作
 */
@Slf4j
public class atomicInteger {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();

        //无参初始值为0
        //log.debug("atomicInteger.get(): {}",atomicInteger.get());

        AtomicInteger atomicInteger1 = new AtomicInteger(5);

        //获取值
        log.debug("get(): {}", atomicInteger1.get());

        //自增并且获取值 <==> ++i
        log.debug("incrementAndGet(): {}", atomicInteger1.incrementAndGet());

        //获取值并且自增 <==> i++
        log.debug("getAndIncrement(): {}", atomicInteger1.getAndIncrement());

        //获取值
        log.debug("get(): {}", atomicInteger1.get());

        //获取值并且加3
        log.debug("getAndAdd(3): {}", atomicInteger1.getAndAdd(3));

        //获取值
        log.debug("get(): {}", atomicInteger1.get());

        //加3并且获取值
        log.debug(":addAndGet(3): {}", atomicInteger1.addAndGet(3));

        //先做运算再读取值
        log.debug("updateAndGet(value -> value * 10): {}", atomicInteger1.updateAndGet(value -> value * 10));


        log.debug("updateAndGet(atomicInteger1, value -> value / 10): {}", updateAndGet(atomicInteger1, value -> value / 10));
    }

    //实现 updateAndGet的原子操作,但是运算单一，没有通用性
    public static int updateAndGet(AtomicInteger atomicInteger) {
        while (true) {
            int prev = atomicInteger.get();

            int next = prev * 10;

            if (atomicInteger.compareAndSet(prev, next)) {
                return next;
            }
        }
    }

    //实现updateAndGet的原子操作,增加其通用性
    public static int updateAndGet(AtomicInteger atomicInteger, IntUnaryOperator operator) {
        while (true) {
            int prev = atomicInteger.get();

            int next = operator.applyAsInt(prev);

            if (atomicInteger.compareAndSet(prev, next)) {
                return next;
            }
        }
    }
}
