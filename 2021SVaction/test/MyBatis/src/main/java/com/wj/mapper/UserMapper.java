package com.wj.mapper;

import com.wj.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /*User selectById(Integer id);

    List<User> findBySex(String sex);*/

   /* List<User> listByUser(User user);

    List<User> listInId(List<Integer> list);

    Integer updateById(User user);

    List<User> listById(User user);*/

   /* User findById(Integer id);*/

    User findByUser(User user);

    List<User> findByNicknameOrPassword(@Param("nickname") String nickname,@Param("password") Integer password);

    List<User> findById(@Param("id") Integer id);
}
