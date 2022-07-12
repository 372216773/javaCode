package jMM;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 退出不出的循环
 */
@Slf4j
public class Demo {
    static Boolean run = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (run) {
                //如果run为真，则一直执行
            }
        }).start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("改变run的值为false");
        run = false;
    }
}
