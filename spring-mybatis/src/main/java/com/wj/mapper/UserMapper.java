package com.wj.mapper;

import com.wj.entity.User;

import java.util.List;

//mybatis中的mapper相当于Dao
//mybatis当中都是接口类型的
public interface UserMapper {
    User selectById(Integer id);

    List<User> selectByName(String name);

    List<User> likeByName(String name);

    int insertUser(User user);

    //where-if
    List<User> listByUser(User user);

    //foreach接收多个参数
    List<User> ListByIdWithForeach(List<Integer> list);

    Integer updateUser(User user);

    List<User> selectById1(User user);

}
