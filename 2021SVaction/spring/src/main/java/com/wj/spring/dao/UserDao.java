package com.wj.spring.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public void add() {
        System.out.println("userDao 的 add()");
    }
}
