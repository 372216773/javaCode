package com.wj.dao;

import com.wj.entity.User;

//mybatis中的mapper相当于Dao
//mybatis当中都是接口类型的
public interface UserMapper {
    User selectById();

    User selectByName();

}
