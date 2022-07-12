package com.wj.project.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.project.goods.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Goods> listByTitleWithNgram(@Param("title") String title, @Param("start") Long start, @Param("size") Long size);

    Long totalByTitleWithNgram(@Param("title") String title);
}
