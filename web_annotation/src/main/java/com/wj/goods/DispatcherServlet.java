package com.wj.goods;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/*
    1.接收客户端的请求
    2.分发请求
 */
public class DispatcherServlet {


    //解析properties配置文件后,得到指定包下的所有类名全称
    public static List<String> allClassNameList = new ArrayList<>();

    @Test
    /**
     * 负责请求的调度
     *
     * @param request
     * @param response
     */
    public void dispatcher() throws IOException {
        baseAnnotation();
    }

    /**
     * 基于注解的解析方式
     */
    private void baseAnnotation() throws IOException {
        //新建资源对象
        Properties properties = new Properties();
        //将资源对象加载进来
        properties.load(DispatcherServlet.class.getClassLoader().getResourceAsStream("application.properties"));
        //读入需要扫描的包名
        String basePackageName = properties.getProperty("scan-package");
        //需要得到所有类名全称,就要进行包扫描,包扫描则需要路径
        //所以要把得到的包名转换成路径
        String basePackagePath = basePackageName.replaceAll("\\.", "/");
        doScan(basePackagePath);
        System.out.println(allClassNameList);
    }

    private void doScan(String basePackagePath) {

        //获取classPath路径在硬盘中的url(路径),这种方式只能识别先对路径
        URL url = DispatcherServlet.class.getClassLoader().getResource(basePackagePath);
        //File对象代表磁盘中实际存在的文件和目录
        //主要用于文件和目录的创建、文件的查找和文件的删除等
        File basePackageFile = new File(url.getFile());
        File[] files = basePackageFile.listFiles();
        //com.wj是父目录,遍历com.wj下的子目录,只有一个goods目录
        for (File file : files) {
            //判断file对象是否为目录
            if (file.isDirectory()) {
                //得到com.wj.goods相对路径
                doScan(basePackagePath+"/"+file.getName());
            }else {
                allClassNameList.add(basePackagePath.replaceAll("/","\\.") +"."+file.getName().replace(".class",""));
            }
        }

    }
}