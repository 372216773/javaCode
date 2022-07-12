package com.wj.springboot.mapper;

import com.wj.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {

    User findById(@Param("id") Integer id);

    Integer updateById(@Param("id") Integer id,@Param("nickname") String nickname);
}
