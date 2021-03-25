package com.wj.spring.entity;

import java.util.Map;

public class User {
    private String nickname;
    private String password;
    private String gender;
    private Map<String,String> map;

    public User() {
        System.out.println("User的构造方法");
    }

    public User(String name, String password,Map<String,String> map) {
        this.nickname = name;
        this.password=password;
        this.map=map;
    }

    /*
        静态工厂创建对象
         */
    public static User newInstance() {
        System.out.println("静态工厂方法");
        return new User();
    }

    public void setName(String name) {
        this.nickname = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private void init() {
        System.out.println("初始化......");
    }

    private void destroy() {
        System.out.println("spring容器关闭");
    }
}
