package com.wj.project.goods.controller;

import com.wj.common.entity.PageResponse;
import com.wj.common.entity.Response;
import com.wj.common.entity.ResponseCode;
import com.wj.project.goods.entity.GoodsCategory;
import com.wj.project.goods.mapper.GoodsCategoryMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品分类模块")
@RestController
@RequestMapping("/category")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @ApiOperation("添加商品的分类")
    @PostMapping("/save")
    public Response save(@RequestBody GoodsCategory goodsCategory) {
        goodsCategoryMapper.insert(goodsCategory);
        Response response = new Response();
        response.code(ResponseCode.SUCCESS).message("分类存储成功");
        return response;
    }

    @ApiOperation("获取全部的分类列表")
    @GetMapping("/listAll")
    public Response listAll() {

        //没有做任何要求,即返回所有数据
        List<GoodsCategory> categoryList = goodsCategoryMapper.selectList(null);
        Response response = new Response();
        response.code(ResponseCode.SUCCESS).message("获取分类列表成功").data(categoryList);
        return response;
    }
}
