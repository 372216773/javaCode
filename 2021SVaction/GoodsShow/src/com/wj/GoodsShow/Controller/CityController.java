package com.wj.GoodsShow.Controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.wj.GoodsShow.Service.CityService;
import com.wj.GoodsShow.Service.ProvinceService;
import com.wj.GoodsShow.entity.City;
import com.wj.GoodsShow.entity.Province;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/city/*")
public class CityController extends HttpServlet {
    CityService cityService = new CityService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String action = requestURI.substring(requestURI.lastIndexOf("/"));
        resp.setContentType("application/json;charset=utf-8");
        switch(action) {
            case "/listByPid":
                int id = Integer.parseInt(req.getParameter("pid"));
                List<City> cityList = cityService.listByPid(id);
                String string = JSON.toJSONString(cityList);
                //
                resp.getWriter().write(string);
                break;
        }
    }
}
