package method;

/**
 * yield: 会使得当前线程让出CPU的使用权限，从 Running-->Runnable
 */
public class yield {
    public static void main(String[] args) {
        Runnable runnable1 = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "-->" + count++);
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                //该线程让出CPU使用权限
                Thread.yield();
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "-->" + count++);
                }
            }
        };

        Thread thread1 = new Thread(runnable1, "t1");
        Thread thread2 = new Thread(runnable2, "t2");

        thread1.start();
        thread2.start();
    }
}
