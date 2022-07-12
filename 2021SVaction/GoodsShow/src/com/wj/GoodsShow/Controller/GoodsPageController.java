package com.wj.GoodsShow.Controller;

import com.wj.GoodsShow.Service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(urlPatterns = "/goodsList/page")
public class GoodsPageController extends HttpServlet {
    GoodsService goodsService = new GoodsService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获得当前页码
        Integer current = Integer.valueOf(req.getParameter("current"));
        //每页商品条数
        Integer size = Integer.valueOf(req.getParameter("size"));

        HashMap<Object, Object> hashMap = new HashMap<>();
        try {
            Integer countNum = goodsService.countNum();
            int pages = (int) Math.ceil(countNum * 1.0 / size);
            //总页码
            hashMap.put("pages", pages);
            //当前页码
            hashMap.put("current", current);
            //获得本页所有商品
            hashMap.put("GoodsList", goodsService.pageAll(current, size));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("ResultMap",hashMap);
        req.getRequestDispatcher("/GoodsList.jsp").forward(req,resp);
    }
}
