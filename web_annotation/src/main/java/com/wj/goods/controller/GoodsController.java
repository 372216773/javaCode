package com.wj.goods.controller;

import com.wj.goods.annotation.Controller;
import com.wj.goods.service.GoodsService;

@Controller("goodsController")
public class GoodsController {
    private GoodsService goodsService;

    public void remove() {
        goodsService.remove();
    }
}
