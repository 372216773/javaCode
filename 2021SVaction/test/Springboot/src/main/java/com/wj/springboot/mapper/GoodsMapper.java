package com.wj.springboot.mapper;

import com.wj.springboot.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface GoodsMapper extends tk.mybatis.mapper.common.Mapper<Goods> {

}
