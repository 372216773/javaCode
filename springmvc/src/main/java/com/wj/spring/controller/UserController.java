package com.wj.spring.controller;

import com.wj.spring.service.UserService;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//设置了@WebServlet注解,当请求该Servlet时,服务器就会自动读取当中的信息
//@WebServlet("/user.do")
//继承HttpServlet,重写service方法来处理请求
@Controller
@RequestMapping("/user")
public class UserController extends HttpServlet {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public ModelAndView login() {
        String str = userService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",str);
        modelAndView.setViewName("/login.jsp");
        return modelAndView;
    }


}
