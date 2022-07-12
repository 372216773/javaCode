package com.wj.ssm;

import com.wj.ssm.entity.User;
import com.wj.ssm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


/* 测试类运行时, 加载 spring 的运行器, 加载 spring 的运行环境(由ApplicationContext创建) */
@RunWith(SpringRunner.class)
/*加载 xml 配置文件, 还是基于Spring 的 xml 的配置*/
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testFindById() {
        User user = userService.findById(1);

        System.out.println(user);
    }
}
