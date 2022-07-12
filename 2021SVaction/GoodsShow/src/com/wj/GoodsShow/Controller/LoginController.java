package com.wj.GoodsShow.Controller;

import com.alibaba.fastjson.JSONObject;
import com.wj.GoodsShow.Service.GoodsService;
import com.wj.GoodsShow.Service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    LoginService loginService = new LoginService();
    GoodsService goodsService = new GoodsService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String result = null;
        try {
            result = loginService.login(name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        if (result != null) {
            jsonObject.put("message", "success");
            jsonObject.put("code", 666);
            jsonObject.put("nickname", result);
            //在session对象域中设置属性(对应一个浏览器),在整个会话过程中都存在,request存在期很短

            req.getSession().setAttribute("nickname", name);
            //执行完就返回到jsp中
        } else {
            jsonObject.put("message", "fail");
            jsonObject.put("code", 888);
        }
        //响应
        resp.setContentType("application/json");
        resp.getWriter().write(String.valueOf(jsonObject));
    }
}
