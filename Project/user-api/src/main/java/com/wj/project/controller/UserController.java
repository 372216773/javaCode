package com.wj.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wj.common.entity.Response;
import com.wj.common.entity.ResponseCode;
import com.wj.common.utils.JwtUtils;
import com.wj.project.entity.User;
import com.wj.project.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation("注册接口")
    @PostMapping("/register")
    public Response register(@RequestBody User user) {
        //对密码进行信息摘要加密(md5)
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
        HashMap<String, Object> map = new HashMap<>();
        Response response = new Response();
        response.code(ResponseCode.SUCCESS).message("注册成功").data(null);
        return response;
    }

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        //对密码进行加密,再做对比
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        User respUser = userMapper.selectOne(wrapper);

        Response response = new Response();
        if (StringUtils.isEmpty(respUser)) {
            response.code(ResponseCode.FAIL).message("登陆失败");
            return response;
        }
        String userId = respUser.getId();
        String token = JwtUtils.getJwtToken(respUser.getId(),respUser.getNickname());
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        response.code(ResponseCode.SUCCESS).message("登陆成功").data(map);
        return response;
    }
}
