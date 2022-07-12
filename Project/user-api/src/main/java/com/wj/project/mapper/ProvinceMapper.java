package com.wj.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.project.entity.Province;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ProvinceMapper extends BaseMapper<Province> {
}
