package com.wj.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.project.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CityMapper extends BaseMapper<City> {
}
