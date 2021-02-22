package com.wj.goods.controller;

import com.alibaba.fastjson.JSON;
import com.wj.goods.entity.Good;
import com.wj.goods.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

@WebServlet("/goods/*")
public class GoodsController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodsService goodsService = new GoodsService();
        try {
            List<Good> goodList = goodsService.listAll();
            //resp.setContentType("application/json;charset=utf8");
            String jsonString = JSON.toJSONString(goodList);

            //resp.getWriter().write(jsonString);
            //LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
            //map.put("good",goodList);
            req.setAttribute("Goods",goodList);
            //转发,可以携带属性
            req.getRequestDispatcher("/goods.jsp").forward(req,resp);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }
}
