package Unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用自定义的 AtomicData 实现之前线程安全的原子整数 Account 实现
 * AtomicInteger 底层也是使用 Unsafe 实现的
 */

public class AccountUnsafeDemo {
    public static void main(String[] args) {
        Account.demo(new MyAtomicInteger(10000));
    }
}

class MyAtomicInteger implements Account {

    //使用 volatile，保证在多线程下的安全性
    private volatile int balance;

    private static Unsafe UNSAFE;

    //偏移量
    private static long balanceOffset;

    public MyAtomicInteger(int balance) {
        this.balance = balance;
    }

    static {
        Field theUnsafe = null;
        try {
            //获得 Unsafe 属性
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //强制访问
        theUnsafe.setAccessible(true);

        try {
            //获得 Unsafe 实例
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            //获得指定字段相对于这个类在起始地址的偏移量
            balanceOffset = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("balance"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer getBalance() {
        return this.balance;
    }

    @Override
    public void withdraw(Integer amount) {
        while (true) {
            int prev = balance;
            int next = prev - amount;

            if (UNSAFE.compareAndSwapInt(this, balanceOffset, prev, next)) {
                break;
            }
        }
    }
}

interface Account {

    //获取金额
    Integer getBalance();

    //取款
    void withdraw(Integer amount);

    /*
    方法内会启动1000个线程,每个线程做-10元的操作
    如果初始余额为10000,那么正确的结果应当是0
     */
    static void demo(Account account) {
        List<Thread> ts = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance()
                + " cost: " + (end - start) / 1000_000 + " ms");
    }
}
