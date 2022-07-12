package com.wj.project.goods.controller;

import com.wj.common.entity.Response;
import com.wj.common.entity.ResponseCode;
import com.wj.common.utils.JwtUtils;
import com.wj.project.goods.entity.Goods;
import com.wj.project.goods.entity.GoodsCart;
import com.wj.project.goods.mapper.GoodsCartMapper;
import com.wj.project.goods.mapper.GoodsMapper;
import com.wj.project.goods.service.GoodsCartService;
import com.wj.project.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "购物车模块")
@RestController
@RequestMapping("/cart")
public class GoodsCartController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsCartMapper goodsCartMapper;

    @Autowired
    private GoodsCartService goodsCartService;

    @ApiOperation("加入购物车")
    @PostMapping("/save")
    public Response save(HttpServletRequest request, @RequestParam("GoodsId") String goodsId, @RequestParam("member") Long member) {
        //1.解析请求对象中的 token,即为userId
        //签名不正确会出异常
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        //拿到用户名
        String nickname = userMapper.selectById(userId).getNickname();

        Goods goods = goodsMapper.selectById(goodsId);
        String title = goods.getTitle();

        Response response = new Response();

        if (goods.getStock()<=0) {
            response.code(ResponseCode.FAIL).message("库存不足");
            return response;
        }

        GoodsCart goodsCart = new GoodsCart();
        goodsCart.setGId(goodsId);
        goodsCart.setGMember(member);
        goodsCart.setGTitle(title);
        goodsCart.setUId(userId);
        goodsCart.setUName(nickname);

        int insert = goodsCartMapper.insert(goodsCart);
        response.code(ResponseCode.SUCCESS).message("加入购物车成功").data(insert);
        return response;
    }

    @ApiOperation("查看我的购物车")
    @GetMapping("/list")
    public Response listByUserId(HttpServletRequest request) {
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        GoodsCart goodsCart = new GoodsCart();
        goodsCart.setUId(userId);
        //未删除的购物车中的商品
        goodsCart.setIsDelete(0);
        List<GoodsCart> goodsCarts = goodsCartService.listByGoodsCart(goodsCart);

        List<Goods> goodsList = new ArrayList<>();

        goodsCarts.forEach(good -> {
            Goods goodByGId = goodsMapper.selectById(good.getGId());
            goodsList.add(goodByGId);
        });

        Response response = new Response();
        response.code(ResponseCode.SUCCESS).message("购物车商品获取成功").data(goodsList);

        return response;
    }

    @ApiOperation("从购物车中删除商品")
    @PostMapping("/remove")
    public Response removeGoods(HttpServletRequest request, @RequestParam("goodsId") String goodsId) {
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        Integer update = goodsCartService.updateStatusByUserIdAndGoodsId(1, userId, goodsId);

        Response response = new Response();
        response.code(ResponseCode.SUCCESS).message("删除成功").data(update);
        return response;
    }
}
