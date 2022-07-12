package com.wj.springboot.controller;

import com.wj.springboot.entity.Goods;
import com.wj.springboot.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsMapper goodsMapper;

    @RequestMapping("/all")
    public ModelAndView listGoods() {
        List<Goods> goods = goodsMapper.selectAll();
        System.out.println(goods);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goodsList",goods);
        modelAndView.setViewName("goods");
        return modelAndView;
    }
}
