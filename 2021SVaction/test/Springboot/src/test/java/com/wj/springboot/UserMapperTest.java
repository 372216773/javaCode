package com.wj.springboot;

import com.wj.springboot.Application;
import com.wj.springboot.entity.User;
import com.wj.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//如果test和java的层级顺序不同,会找不到@SpringBootApplication,所以需要手动指定
// @SpringBootTest(classes = Application.class)
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void listAll() {
        List<User> users = userMapper.selectAll();
        System.out.println(users);
    }
}
