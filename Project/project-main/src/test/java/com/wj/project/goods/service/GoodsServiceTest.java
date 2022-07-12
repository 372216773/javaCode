package com.wj.project.goods.service;

import com.wj.project.goods.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
//@RunWith(JUnit4.class)
//就是指用JUnit4测试工具来运行
@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class),让测试类运行于Spring测试环境，并使用JUnit4测试工具来运行测试。
//才能注入属性
public class GoodsServiceTest {

    @Autowired
    GoodsService goodsService;

    @Test
    public void testListByTitleWithNgram() {
        System.out.println(goodsService.listByTitleWithNgram("hu",1L,1L));
    }
}
