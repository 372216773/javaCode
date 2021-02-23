package com.wj.goods.service;

import com.wj.goods.entity.Good;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class GoodServiceTest {


    GoodsService goodsService = new GoodsService();

    @Test
    public void testGoods () throws SQLException {

        List<Good> goodList = goodsService.listAll();
        System.out.println(goodList);
    }

    @Test
    public void testAddGoods() throws SQLException {

        int good = goodsService.addGood("小蜜蜂", 23, "huzi.jpg");
        System.out.println(good);

    }

}
