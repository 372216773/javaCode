package com.wj.goods.controller;

import com.wj.goods.service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //将请求的数据设置为utf8编码格式,中文乱码
        req.setCharacterEncoding("utf8");

        //获得用户的所有属性
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        int province = Integer.parseInt(req.getParameter("province"));
        int city = Integer.parseInt(req.getParameter("city"));
        String extra = req.getParameter("extra");

        RegisterService registerService = new RegisterService();

        try {
            registerService.register(nickname, password, province, city, extra);
        } catch (SQLException | NoSuchAlgorithmException throwable) {
            throwable.printStackTrace();
        }

        //重定向到登陆界面,相当于两次请求响应,url会发生改变
        resp.sendRedirect("/login.jsp");

    }
}
