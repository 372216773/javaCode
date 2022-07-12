package com.wj.project.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.project.goods.entity.GoodsCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface GoodsCartMapper extends BaseMapper<GoodsCart> {
}
