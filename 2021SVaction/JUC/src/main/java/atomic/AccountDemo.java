package atomic;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 引出问题
 */
public class AccountDemo {
    public static void main(String[] args) {

        //不安全，会出现指令交错
        //Account.operate(new AccountUnsafe(10000));

        //synchronized
        //Account.operate(new AccountSafeLocked(10000));

        //AtomicInteger
        Account.operate(new AccountSafeNoLock(10000));

    }
}

/**
 * 不安全，会出现指令交错,出现并发错误
 */
class AccountUnsafe implements Account {

    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return this.balance;
    }

    @Override
    public void withdraw(Integer amount) {
        balance -= amount;
    }
}

/**
 * 加锁，确保临界区代码的原子性，使其同步进行
 */
class AccountSafeLocked implements Account {

    private Integer balance;

    public AccountSafeLocked(Integer balance) {
        this.balance = balance;
    }

    @Override
    public synchronized Integer getBalance() {
        return this.balance;
    }

    @Override
    public synchronized void withdraw(Integer amount) {
        this.balance -= amount;
    }
}

/**
 * 使用原子整数
 */
class AccountSafeNoLock implements Account {

    //使用JDK提供的基于无锁的实现,这个实现就是AtomicInteger(原子整数)
    private AtomicInteger balance;

    public AccountSafeNoLock(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return this.balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while (true) {
            int prev = balance.get();
            int next = prev - amount;
            //prev 与主存中的值进行比较，如果相等才会修改为next值
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}

interface Account {

    //获取余额
    Integer getBalance();

    //取款
    void withdraw(Integer amount);

    /**
     * 方法内会启动 1000 个线程, 每个线程做 -10 元的操作
     * 如果初始余额为 10000, 那么正确的结果应当是 0
     */
    static void operate(Account account) {
        ArrayList<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            threadList.add(new Thread(() -> account.withdraw(10)));
        }

        long start = System.nanoTime();
        //启动所有线程
        threadList.forEach(Thread::start);

        //等待所有线程执行完毕，再放行
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();
        System.out.println("balance: " + account.getBalance() + " cost:" + (end - start) / 1000_000 + " ms");

    }
}
