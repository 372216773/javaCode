package createThread.FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTask2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyCall());

        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }
}

class MyCall implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        System.out.println("MyCall 实现 Callable 的 call()");
        return 200;
    }
}