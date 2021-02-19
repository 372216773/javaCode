package com.wj.goods.controller;

import com.alibaba.fastjson.JSON;
import com.wj.goods.entity.City;
import com.wj.goods.service.CityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//对传进来的相关请求进行调用Service里的方法
//city下的所有路径
@WebServlet("/city/*")
public class CityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String substring = requestURI.substring(requestURI.lastIndexOf("/"));

        switch (substring) {
            case "/listByPid" :
                try {
                    List<City> cities = CityService.listByPid();
                    resp.setContentType("application/json;charset=utf8");
                    //转换为字符串
                    String string = JSON.toJSONString(cities);
                    resp.getWriter().write(string);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }
}