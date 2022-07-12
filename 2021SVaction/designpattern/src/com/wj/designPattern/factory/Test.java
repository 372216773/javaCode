package com.wj.designPattern.factory;

/**
 * 抽象工厂方法
 */
interface Food {
    void eat();
}

interface Drink {
    void drink();
}

interface theFactory {
    Food getFood();

    Drink getDrink();
}

class DouJiang implements Drink {

    @Override
    public void drink() {
        System.out.println("喝豆浆");
    }
}

class YouTiao implements Food {

    @Override
    public void eat() {
        System.out.println("吃油条");
    }
}

class SanQinFactory implements theFactory {

    @Override
    public Food getFood() {
        return new YouTiao();
    }

    @Override
    public Drink getDrink() {
        return new DouJiang();
    }
}

public class Test {
    public static void main(String[] args) {
        new SanQinFactory().getFood().eat();
    }
}
