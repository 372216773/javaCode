package createThread.Runnable;

public class Runnable1 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                System.out.println("Runnable running");
            }
        };

        Thread thread = new Thread(runnable);

        thread.start();
    }
}
