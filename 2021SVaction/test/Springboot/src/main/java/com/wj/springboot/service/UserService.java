package com.wj.springboot.service;

import com.wj.springboot.entity.User;
import com.wj.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    //会有红线,因为Mapper加的是Mybatis的注解,Spring不认识,给mapper加上一个Spring的注解即可
    @Autowired
    private UserMapper userMapper;

    public User findById(Integer id) {
        return userMapper.findById(id);
    }


    //进行事务管理(不自动提交,方法内一旦执行出错,数据不会改变)
    @Transactional
    public Integer updateById(Integer id,String nickname) {
        return userMapper.updateById(id,nickname);
    }
}
