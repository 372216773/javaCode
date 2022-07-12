package com.wj.designPattern.Singleton.hungry;

public class Demo {
    private Demo(){}

    private static Demo demo=new Demo();

    public static Demo getDemo(){
        return demo;
    }
}
