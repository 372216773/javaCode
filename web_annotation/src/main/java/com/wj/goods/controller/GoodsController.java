package com.wj.goods.controller;

import com.wj.goods.annotation.Controller;
import com.wj.goods.annotation.Qualifier;
import com.wj.goods.annotation.RequestMapping;
import com.wj.goods.service.GoodsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller()
@RequestMapping("/goods")
public class GoodsController {

    @Qualifier()
    private GoodsService goodsService;

    @RequestMapping("remove")
    public void remove(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("删除操作");
    }

    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("添加操作");
    }

    @RequestMapping("//modify")
    public void modify(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("修改操作");
    }

}
