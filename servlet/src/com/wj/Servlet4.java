package com.wj;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class Servlet4 extends HttpServlet {

    private static Object query;
    private static int data;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        //获取注册Servlet时配置的初始化参数
        System.out.println(config.getInitParameter("myName"));

        //获取全局配置的context-param初始化参数,(可以配置多个,但是建议配置一个)
        System.out.println("全局初始化参数: " + getServletContext().getInitParameter("myName1"));

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //200 --> ok
        System.out.println(resp.getStatus());

        //设置响应头数据
        resp.setHeader("aa","admin");
        resp.setHeader("bb","admin123");

        //设置http状态码,如果这个状态码不设置,服务器会根据自己内部的来设置
        resp.setStatus(404);

        //设置响应实体
        //表示返回的格式,html还是xml这些
        resp.setContentType("text/html");
        //设置编码格式的
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("<!DOCTYPE html><head><meta charset='UTF-8'><title>goods</title> <style>.c1{background-color:#f6b4c0; height:40px; width:160px; margin:0 auto;text-align:center; line-height:40px;}</style></head><body><div class='c1'>goods</div><div>" + data + "</div></body></html>");

    }

    public static void main(String[] args) throws Exception{

        //创建资源对象
        Properties properties = new Properties();

        //给资源对象加载配置好的数据
        properties.load(Servlet4.class.getClassLoader().getResourceAsStream("druid.properties"));

        //jar:druid连接池
        //创建连接池对象,配置好的资源对象传给连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        //jar:commons-DBUtils  -->对JDBC封装
        //创建DBUtils的核心操作类,要操作对象为dataSource
        QueryRunner queryRunner = new QueryRunner(dataSource);

        //
        String sql = "select * from goods where id > 0";
        Object query =  queryRunner.query(sql, new MapListHandler());
        System.out.println(query);
    }
}
