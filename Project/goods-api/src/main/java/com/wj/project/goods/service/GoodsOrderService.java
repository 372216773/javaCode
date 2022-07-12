package com.wj.project.goods.service;

import com.wj.common.entity.Response;
import com.wj.project.entity.Address;
import com.wj.project.entity.User;
import com.wj.project.goods.entity.Goods;
import com.wj.project.goods.entity.GoodsOrder;
import com.wj.project.goods.entity.vo.OrderVo;
import com.wj.project.goods.mapper.GoodsMapper;
import com.wj.project.goods.mapper.GoodsOrderMapper;
import com.wj.project.mapper.AddressMapper;
import com.wj.project.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsOrderService {

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private GoodsCartService goodsCartService;

    @Transactional
    public Long save(String userId, OrderVo orderVo) throws Exception {

        User user = userMapper.selectById(userId);
        String nickname = user.getNickname();

        Goods goods = goodsMapper.selectById(orderVo.getGoodsId());

        String title = goods.getTitle();
        Long currentPrice = goods.getCurrentPrice();
        //查询商品表, 根据库存进行判断
        if (goods.getStock() < orderVo.getNumber()) {
            throw new Exception(title + "库存不足");
        }

        //添加到订单表
        GoodsOrder goodsOrder = new GoodsOrder();

        goodsOrder.setUId(userId);
        goodsOrder.setUName(nickname);
        goodsOrder.setGId(orderVo.getGoodsId());
        goodsOrder.setGTitle(title);
        goodsOrder.setAddrId(orderVo.getAddrId());
        goodsOrder.setNumber(orderVo.getNumber());
        goodsOrder.setCurrentPrice(currentPrice);
        long totalPrice = currentPrice * orderVo.getNumber();
        goodsOrder.setTotalPrice(totalPrice);
        goodsOrder.setStatus(0);

        goodsOrderMapper.insert(goodsOrder);

        //删除购物车中的商品
        goodsCartService.updateStatusByUserIdAndGoodsId(1,userId,goods.getId());

        //库存减少
        goods.setStock(goods.getStock()-orderVo.getNumber());
        goodsMapper.updateById(goods);

        return totalPrice;
    }
}
