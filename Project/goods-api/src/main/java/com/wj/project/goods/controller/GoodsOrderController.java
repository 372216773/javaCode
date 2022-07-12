package com.wj.project.goods.controller;

import com.wj.common.entity.Response;
import com.wj.common.entity.ResponseCode;
import com.wj.common.utils.JwtUtils;
import com.wj.project.goods.entity.vo.OrderVo;
import com.wj.project.goods.service.GoodsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Api(tags = "订单模块")
@RestController
@RequestMapping("/order")
public class GoodsOrderController {

    @Autowired
    private GoodsOrderService goodsOrderService;

    @ApiOperation("创建订单")
    @PostMapping("/save")
    //一个商品对应一个订单,一次可能支付多个商品
    public Response save(HttpServletRequest request, @RequestBody List<OrderVo> orderVoList) throws Exception {

        String userId = JwtUtils.getMemberIdByJwtToken(request);

        Long totalPrice = 0L;
        for (OrderVo orderVo:orderVoList) {
            totalPrice += goodsOrderService.save(userId,orderVo);
        }
        HashMap<String, Long> map = new HashMap<>();
        map.put("totalPrice",totalPrice);

        Response response = new Response();
        response.code(ResponseCode.SUCCESS).message("订单创建成功").data(map);
        return response;
    }
}
