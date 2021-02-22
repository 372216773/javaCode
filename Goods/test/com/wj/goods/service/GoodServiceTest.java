package com.wj.goods.service;

import com.wj.goods.entity.Good;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class GoodServiceTest {

    @Test
    public void testGoods () throws SQLException {

        GoodsService goodsService = new GoodsService();
        List<Good> goodList = goodsService.listAll();
        System.out.println(goodList);
    }

}
