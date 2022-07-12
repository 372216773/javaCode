package com.wj.project.goods.controller;


import com.wj.common.entity.PageResponse;
import com.wj.common.entity.Response;
import com.wj.common.entity.ResponseCode;
import com.wj.project.goods.entity.Goods;
import com.wj.project.goods.mapper.GoodsMapper;
import com.wj.project.goods.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Api(tags = "商品模块")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("添加商品")
    @PostMapping("/save")
    //post方式 普通的 form 提交表单
    public Response save(@RequestParam("goodsImage") MultipartFile multipartFile, Goods goods) throws IOException {

        Response response = goodsService.save(multipartFile, goods);
        return response;
    }

    @ApiOperation("获取待审批的商品列表")
    @GetMapping("/list/pending")
    public PageResponse listUnPendingGoods(@RequestParam("current") Long current, @RequestParam("size") Long size) {
        Goods goods = new Goods();
        goods.setStatus(0);
        PageResponse response = goodsService.listByGoods(current, size, goods);
        return response;
    }

    @ApiOperation("获取通过审批的商品列表")
    @GetMapping("/list/passed")
    public PageResponse listPassedGoods(@RequestParam("current") Long current, @RequestParam("size") Long size) {
        Goods goods = new Goods();
        goods.setStatus(0);
        PageResponse response = goodsService.listByGoods(current, size, goods);
        return response;
    }

    @ApiOperation("获取未通过的商品列表")
    @GetMapping("/list/refused")
    public PageResponse listRefusedGoods(@RequestParam("current") Long current, @RequestParam("size") Long size) {
        Goods goods = new Goods();
        goods.setStatus(1);
        PageResponse response = goodsService.listByGoods(current, size, goods);
        return response;
    }

    @ApiOperation("获取未通过的商品列表")
    @GetMapping("/list/all")
    public PageResponse listAllGoods(@RequestParam("current") Long current, @RequestParam("size") Long size) {
        //所有商品不需要限定商品参数
        PageResponse response = goodsService.listByGoods(current, size, null);
        return response;
    }

    @ApiOperation("部门领导通过商品审批")
    @GetMapping("/pass")
    public Response pass(@RequestParam("id") String id) {
        Goods goods = new Goods();
        //作为查找的依据
        goods.setId(id);
        //要修改的数据
        goods.setStatus(1);
        goods.setGmtModify(new Date());
        //会把goods里边的id取出来作为条件查询
        goodsMapper.updateById(goods);
        Response response = new Response();
        response.code(ResponseCode.SUCCESS).message("已通过审批");
        return response;
    }

    @ApiOperation("获取分类后的商品列表")
    @GetMapping("/category")
    public PageResponse listGoodsByCategory(Long current, Long size, String cId) {
        PageResponse pageResponse = goodsService.listGoodsByCategory(current, size, cId);

        return pageResponse;
    }

    @ApiOperation("根据商品id获取商品详情")
    @GetMapping("/detail/{id}")
    public Response findById(@PathVariable("id") String id) {
        Goods good = goodsMapper.selectById(id);
        Response response = new Response();
        response.code(ResponseCode.SUCCESS).message("商品获取成功").data(good);
        return response;
    }

    @ApiOperation("根据商品标题搜索商品列表")
    @GetMapping("/title/search")
    public PageResponse listByTitleWithNgram(@RequestParam("title") String title, @RequestParam("current") Long current, @RequestParam("size") Long size) {
        return goodsService.listByTitleWithNgram(title, current, size);
    }

    @ApiOperation("根据商品id修改商品信息")
    @PostMapping("/modify")
    public Response modifyById(@RequestParam("id") String id,@RequestBody Goods goods) {
        goods.setId(id);
        goodsMapper.updateById(goods);
        Response response = new Response();
        response.code(ResponseCode.SUCCESS).message("商品修改成功");
        return response;
    }

    @ApiOperation("根据商品id删除商品")
    @DeleteMapping("/delete")
    public Response removeById(@RequestParam("id") String id) {
        int delete = goodsMapper.deleteById(id);
        Response response = new Response();
        response.code(ResponseCode.SUCCESS).message("商品修改成功").data(delete);
        return response;
    }

}