package Demo;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntBinaryOperator;

public final class Demo {
    public static void main(String[] args) {
        children children = new children();

        new Thread(()->{
            children.method();
        }).start();
    }
}