package com.wj.designPattern.proxy.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface calc {
    void add(int a, int b);

    void sub(int a, int b);

    void mul(int a, int b);

    void div(int a, int b);
}

class MyCalc implements calc {

    @Override
    public void add(int a, int b) {
        System.out.println(a + b);
    }

    @Override
    public void sub(int a, int b) {
        System.out.println(a - b);
    }

    @Override
    public void mul(int a, int b) {
        System.out.println(a * b);
    }

    @Override
    public void div(int a, int b) {
        System.out.println(a / b);
    }
}

interface Interceptor {
    void before();

    void after();
}

class MyInterceptor1 implements Interceptor {

    @Override
    public void before() {
        System.out.println("风格1：method() start");
    }

    @Override
    public void after() {
        System.out.println("风格1：method() end");
    }
}

class MyInterceptor2 implements Interceptor {

    @Override
    public void before() {
        System.out.println("风格2：方法开始");
    }

    @Override
    public void after() {
        System.out.println("风格2：方法结束");
    }
}

class MyHandler implements InvocationHandler {

    private MyCalc myCalc;
    private Interceptor interceptor;

    public MyHandler(MyCalc myCalc, Interceptor myInterceptor) {
        this.myCalc = myCalc;
        this.interceptor = myInterceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        interceptor.before();

        method.invoke(myCalc, args);

        interceptor.after();
        return 0;
    }
}

class myProxy {

    public static Object getProxy(MyCalc myCalc, Interceptor interceptor) {
        ClassLoader classLoader = myCalc.getClass().getClassLoader();

        Class<?>[] interfaces = myCalc.getClass().getInterfaces();

        return Proxy.newProxyInstance(classLoader, interfaces, new MyHandler(myCalc, interceptor));
    }
}

public class Test {
    public static void main(String[] args) {
        MyCalc myCalc = new MyCalc();

        calc proxyCalc = (calc) myProxy.getProxy(myCalc, new MyInterceptor2());

        proxyCalc.add(1,2);
    }
}