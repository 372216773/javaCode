package createThread.Thread;

public class Thread1 {
    public static void main(String[] args) {

        //创建线程对象
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                System.out.println("my thread1 running...");
            }
        };

        thread1.start();
    }
}
