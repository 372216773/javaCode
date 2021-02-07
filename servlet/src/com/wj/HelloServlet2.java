package com.wj;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class HelloServlet2 extends HttpServlet {

    //接收任何请求
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        System.out.println("HelloServlet2 coming......");
        res.getWriter().write("<div style='color:blue' >html</div>");
        res.setCharacterEncoding("utf-8");
        res.getWriter().write("<!DOCTYPE html><head><meta charset='UTF-8'><title>goods</title></head><body><div>中国</div></body></html>");

    }
}
