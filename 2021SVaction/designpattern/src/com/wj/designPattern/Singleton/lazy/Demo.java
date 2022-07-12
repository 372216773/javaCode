package com.wj.designPattern.Singleton.lazy;

public class Demo {
    private Demo(){}

    private static Demo demo;

    public static Demo getLazy() {
        if (demo==null) {
            demo=new Demo();
        }
        return demo;
    }
}
