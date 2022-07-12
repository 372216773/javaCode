package com.wj.common.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("商品管理平台接口")//api标题
                .description("商品管理平台接口描述")//api描述
                .version("1.0.0")//版本号
                /*.contact(new Contact("王京","地址","邮箱"))*/
                .build();
        return new Docket(DocumentationType.SWAGGER_2)//文档类型（swagger2）
                .apiInfo(apiInfo)//设置包含在json ResourceListing响应中的api元信息
                .select()//启动用于api选择的构建器
                .apis(RequestHandlerSelectors.basePackage("com.wj"))//需要扫描的包
                .paths(PathSelectors.any())//路径过滤器（扫描所有路径）
                .build();
    }

}