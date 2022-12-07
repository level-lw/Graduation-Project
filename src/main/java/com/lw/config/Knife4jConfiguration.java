package com.lw.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @project: Graduation-Project
 * @Description: 创建Swagger配置依赖
 * @Author: 李伟
 * @Create: 2022/11/18 10:02
 * @Version: 1.0
 */

@Slf4j
@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
public class Knife4jConfiguration {

    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        //指定使用Swagger2规范
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        // 标题
                        .title("毕业生离校管理系统 后台服务API接口文档")
                        //描述字段支持Markdown语法
                        .description("# 毕业生离校管理系统测试")
                        // 服务url
                        .termsOfServiceUrl("https://doc.xiaominfo.com/")
                        // 作者
                        .contact("13689228486@163.com")
                        // 版本
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("用户服务")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.lw.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
