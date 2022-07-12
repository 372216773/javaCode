package com.wj.GoodsShow.service;

import com.wj.GoodsShow.Service.GoodsService;
import com.wj.GoodsShow.entity.Goods;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class GoodsServiceTest {

    @Test
    public void testListAll() throws SQLException {
        GoodsService goodsService = new GoodsService();
        List<Goods> goodsList = goodsService.pageAll(1, 2);
        System.out.println(goodsList);
    }

    @Test
    public void testSum() throws SQLException {
        GoodsService goodsService = new GoodsService();
        List<Goods> goods = goodsService.pageAll(3, 5);
        Integer num = goodsService.currentNum();
        System.out.println(num);
    }

    @Test
    public void testReplaceGood() throws SQLException {
        GoodsService goodsService = new GoodsService();
        System.out.println(goodsService.replaceGood("1351818155867058177", " ROCK ", "38", 123, "acsac"));
    }

}
