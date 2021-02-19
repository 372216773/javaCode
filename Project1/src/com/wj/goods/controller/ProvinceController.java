package com.wj.goods.controller;

import com.alibaba.fastjson.JSON;
import com.wj.goods.entity.Province;
import com.wj.goods.service.ProvinceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//对传进来的相关请求进行调用Service里的方法
@WebServlet("/province/*")
public class ProvinceController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        String substring = requestURI.substring(requestURI.lastIndexOf("/"));
        switch (substring) {
            case "/list" :
                try {
                    List<Province> provinces = ProvinceService.listAll();
                    String string = JSON.toJSONString(provinces);
                    resp.setContentType("application/json;charset=utf8");
                    resp.getWriter().write(string);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
