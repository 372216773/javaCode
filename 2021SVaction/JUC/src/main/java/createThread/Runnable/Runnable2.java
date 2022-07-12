package createThread.Runnable;

public class Runnable2 implements Runnable{
    public static void main(String[] args) {
        Runnable2 runnable2 = new Runnable2();

        Thread thread = new Thread(runnable2);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Runnable running");
    }
}
