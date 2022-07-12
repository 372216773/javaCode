package com.wj.GoodsShow.Controller;

import com.wj.GoodsShow.Service.RegistService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/regist")
public class RegistController extends HttpServlet {

    private RegistService registService = new RegistService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //把接收过来的数据以utf-8进行编码
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Integer pid =Integer.parseInt(req.getParameter("province"));
        Integer cid = Integer.parseInt(req.getParameter("city"));
        String extra = req.getParameter("extra");
        try {
            registService.regist(name,password,pid,cid,extra);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //请求转发,响应重定向
        resp.sendRedirect("/login.jsp");
    }
}
