package method;

import java.util.concurrent.TimeUnit;

/**
 * 两种sleep 使调用线程休眠4秒，进入阻塞状态(Timed Waiting),结束后进入可运行状态(Runnable)
 * 1.Thread.sleep(4000);
 * 2.TimeUnit.SECONDS.sleep(4);
 */
public class sleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Runnable running");
            }
        };


        Thread thread = new Thread(runnable,"t1");
        thread.start();
        System.out.println(thread.getName()+" "+thread.getState());

        TimeUnit.SECONDS.sleep(1);
        System.out.println(thread.getName()+" "+thread.getState());

        TimeUnit.SECONDS.sleep(1);
        System.out.println(thread.getName()+" "+thread.getState());

        TimeUnit.SECONDS.sleep(1);
        System.out.println(thread.getName()+" "+thread.getState());
    }
}
