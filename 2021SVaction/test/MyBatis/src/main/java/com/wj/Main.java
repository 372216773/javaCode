package com.wj;

import com.wj.mapper.UserMapper;
import com.wj.entity.User;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //创建SqlSessionFactory 对象
        InputStream resourceAsStream = Main.class.getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //创建SqlSession对象, 默认不会自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //返回有jdk底层生成的动态代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> user = userMapper.findById(3);

        System.out.println(user);
    }
}
