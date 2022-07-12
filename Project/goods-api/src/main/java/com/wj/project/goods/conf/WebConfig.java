package com.wj.project.goods.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//交给spring管理
@Configuration
//外部访问静态资源时将资源文件的位置映射到 file(文件协议) d:/upload/
public class WebConfig implements WebMvcConfigurer {

    //资源处理器
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //添加注册器,将资源文件的位置映射到 file(文件协议) d:/upload/
        registry.addResourceHandler("/**").addResourceLocations("file:/d:/upload/");
    }
}
