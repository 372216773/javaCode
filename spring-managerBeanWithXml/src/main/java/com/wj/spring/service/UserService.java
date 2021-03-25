package com.wj.spring.service;

import com.wj.spring.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("renameService")
public class UserService {

    @Autowired //按类型注入
    private UserDao userDao;

    public void save() {
        userDao.add();
    }
}
