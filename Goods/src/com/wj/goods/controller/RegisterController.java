package com.wj.goods.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获得用户的所有属性
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        String province = req.getParameter("province");
        String city = req.getParameter("city");
        String extra = req.getParameter("extra");

    }
}
