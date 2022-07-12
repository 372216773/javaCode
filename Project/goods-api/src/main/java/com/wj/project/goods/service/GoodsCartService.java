package com.wj.project.goods.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wj.project.goods.entity.GoodsCart;
import com.wj.project.goods.mapper.GoodsCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsCartService {

    @Autowired
    private GoodsCartMapper goodsCartMapper;

    public List<GoodsCart> listByGoodsCart(GoodsCart goodsCart) {
        //以实体类不为空的列作为条件
        QueryWrapper<GoodsCart> queryWrapper = new QueryWrapper<>(goodsCart);
        List<GoodsCart> goodsCarts = goodsCartMapper.selectList(queryWrapper);
        return goodsCarts;
    }

    public Integer updateStatusByUserIdAndGoodsId(int status, String userId, String goodsId) {
        GoodsCart goodsCart = new GoodsCart();
        goodsCart.setIsDelete(status);
        //update 1:set 2:where
        UpdateWrapper<GoodsCart> wrapper = new UpdateWrapper<>();
        wrapper.eq("u_id",userId)
                .eq("g_id",goodsId);
        int update = goodsCartMapper.update(goodsCart, wrapper);
        return update;
    }
}
