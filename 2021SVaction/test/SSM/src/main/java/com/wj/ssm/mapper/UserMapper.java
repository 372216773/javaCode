package com.wj.ssm.mapper;

import com.wj.ssm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
/*会给注解的类生成代理对象*/
public interface UserMapper {

    User findById(@Param("id") Integer id);

    User findByName(String name);
}
