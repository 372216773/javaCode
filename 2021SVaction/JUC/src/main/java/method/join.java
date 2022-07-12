package method;

/**
 * 哪个线程调用join()方法，就等待哪个线程结束，然后再去执行其他线程。
 */
public class join {

    private static int num = 0;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("开始");


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                num = 10;
            }
        },"t");

        thread.start();
        //所有的线程等待调用了join()的线程执行完，再去执行
        thread.join();

        //1.最多等待3s，在等待时间内完成就直接执行下边的语句，不会继续等待
        //thread.join(3000);

        //TimeUnit.SECONDS.sleep(1);
        // 看main线程和t线程谁先执行到打印语句
        System.out.println(num);
    }
}
