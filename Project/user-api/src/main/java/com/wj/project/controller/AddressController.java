package com.wj.project.controller;

import com.wj.common.entity.Response;
import com.wj.common.entity.ResponseCode;
import com.wj.common.utils.JwtUtils;
import com.wj.project.entity.vo.AddressVo;
import com.wj.project.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "地址模块")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation("保存地址")
    @PostMapping("/save")
    //request 包含 token
    public Response add(HttpServletRequest request, @RequestBody AddressVo addressVo) {

        String userId = JwtUtils.getMemberIdByJwtToken(request);
        Integer integer = addressService.save(userId, addressVo.getProvinceId(), addressVo.getCityId(), addressVo.getCountyId(), addressVo.getDetail());

        Response response = new Response();
        response.code(ResponseCode.SUCCESS).message("地址保存成功").data(integer);
        return response;
    }
}
