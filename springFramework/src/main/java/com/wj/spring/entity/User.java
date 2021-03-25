package com.wj.spring.entity;

public class User {
    private String name;
    private String password;
    private String gender;

    public User() {
        System.out.println("User的构造方法");
    }

    /*
    静态工厂创建对象
     */
    public static User newInstance() {
        System.out.println("静态工厂方法");
        return new User();
    }

}
