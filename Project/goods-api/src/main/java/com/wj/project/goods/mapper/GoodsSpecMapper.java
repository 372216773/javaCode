package com.wj.project.goods.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.project.goods.entity.GoodsSpec;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface GoodsSpecMapper extends BaseMapper<GoodsSpec> {
}
