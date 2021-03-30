package com.wj.spring.controller;

import com.sun.deploy.net.HttpResponse;
import com.wj.spring.entity.User;
import com.wj.spring.service.UserService;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


//设置了@WebServlet注解,当请求该Servlet时,服务器就会自动读取当中的信息
//@WebServlet("/user.do")
//继承HttpServlet,重写service方法来处理请求


@Controller

//url匹配方法
@RequestMapping("/user")
public class UserController extends HttpServlet {

    @Autowired
    UserService userService;


    /*获取参数:*/
    /*1.(String nickname, String password) url?参数 也属于表单类型的数据
    不使用注解进行接收时,要保证形参名与url中的参数名相同(不用加注解,不繁琐,但是要改名字,就要改两处,麻烦)*/
    /*2.(@RequestParam("name") String nickname,@RequestParam("password") String password)
    用springMVC提供的注解(@RequestParam)来接收参数,
    注解中的参数名要与url中的参数名相同(适用于少量参数,繁琐,但是灵活)*/
    /*获取Rest请求风格的参数(路径参数)*/
    /*1.@RequestMapping("/login/{name}/{password},将{name}作为name的值,将{password}作为password的值")
    * 2.(@PathVariable("name"),//赋值 String name,@PathVariable("password")//赋值 String password)*/
    //@PostMapping("/login1")
    //application/x-www-form-urlencoded 表单提交 用 RequestParam(用来接收表单类型的数据)
    //获取List类型的数据
    public ModelAndView login1(@RequestParam("list") List<String> list) {
        System.out.println(list);
        Map<String, Object> map = userService.list();
        ModelAndView modelAndView = new ModelAndView();

        //model
        modelAndView.addObject("list",map);

        //view,逻辑视图(地址)
        modelAndView.setViewName("/login.jsp");
        return modelAndView;
    }

    /*
    * 返回值为String类型的数据时,作为jsp的路径使用
    * 默认返回值都是作为路径的*/
    @PostMapping("/login")
    /*作用:*/
    @ResponseBody
    public User login(@RequestBody User user) {

        System.out.println(user);

        return user;
    }


}
