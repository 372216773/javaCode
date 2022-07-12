package com.wj.project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//如果包的路径名称不统一,就要配置注解扫描包的路径,Spring默认扫描的是当前启动类所在的包下的所有注解
//@ComponentScan(basePackages = {"com.wj.project"})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
