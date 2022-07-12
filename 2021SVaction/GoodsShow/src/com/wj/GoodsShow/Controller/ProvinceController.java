package com.wj.GoodsShow.Controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.wj.GoodsShow.Service.ProvinceService;
import com.wj.GoodsShow.entity.Province;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/province/*")
public class ProvinceController extends HttpServlet {
    ProvinceService provinceService = new ProvinceService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String action = requestURI.substring(requestURI.lastIndexOf("/"));
        resp.setContentType("application/json;charset=utf-8");
        switch(action) {
            case "/list":
                List<Province> list = provinceService.listAll();
                String string = JSON.toJSONString(list);
                //写数据
                resp.getWriter().write(string);
                break;
        }
    }
}
