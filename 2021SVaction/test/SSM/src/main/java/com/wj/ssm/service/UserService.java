package com.wj.ssm.service;

import com.wj.ssm.entity.User;
import com.wj.ssm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findById(Integer id) {
        User user = userMapper.findById(id);

        return user;
    }

    public User findByName(String name) {
        return userMapper.findByName(name);
    }
}
