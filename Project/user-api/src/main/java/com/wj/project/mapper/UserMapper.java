package com.wj.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.project.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper
/*与TKMapper使用方法类似*/
public interface UserMapper extends BaseMapper<User> {
}
