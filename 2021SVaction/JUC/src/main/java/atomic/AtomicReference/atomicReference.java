package atomic.AtomicReference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用，使用原子引用来保证小数类型的共享变量操作的安全性
 */
public class atomicReference {
    public static void main(String[] args) {
        AccountByAtomicReference accountByAtomicReference = new AccountByAtomicReference(10000);

        Account.operate(accountByAtomicReference);
    }
}

class AccountByAtomicReference implements Account{

    /**
     * BigDecimal 保证小数的精确运算
     * AtomicReference 保证小数类型的共享变量操作的安全性
     */
    private final AtomicReference<BigDecimal> balance;

    public AccountByAtomicReference(int balance) {
        this.balance = new AtomicReference<>(new BigDecimal(balance));
    }

    @Override
    public BigDecimal getBalance() {
        return this.balance.get();
    }

    @Override
    public void withdraw(BigDecimal amount) {
        while (true) {
            BigDecimal prev = this.balance.get();
            BigDecimal next = prev.subtract(amount);
            if (balance.compareAndSet(prev,next)) {
                return;
            }
        }
    }
}

interface Account {

    //获取金额
    BigDecimal getBalance();

    //取款

    //Java在java.math包中提供的API类BigDecimal，用来对超过16位有效位的数进行精确的运算。不安全
    void withdraw(BigDecimal amount);

    /**
     * 1000个线程，每次取款10元
     *
     * @param account 账户
     */
    public static void operate(Account account) {

        //定义一个存放线程的表
        ArrayList<Thread> threadList = new ArrayList<>();

        //添加数据
        for (int i = 0; i < 1000; i++) {
            threadList.add(new Thread(() -> {
                //TEN，ONE预设的数值10和1，为省去对常用数值进行新建对象的反复过程，提高效率。
                account.withdraw(BigDecimal.TEN);
            }));
        }

        long start = System.nanoTime();

        //开启所有线程
        threadList.forEach(Thread::start);

        //main 线程等待所有 thread 运行完
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.nanoTime();
        System.out.println("balance: " + account.getBalance() + " cost:" + (end - start)/100_000);

    }
}