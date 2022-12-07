package com.lw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: Graduation-Project
 * @description: 跨域配置类
 * @author: 李伟
 * @create: 2022-12-02 23:35
 **/

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Bean
    WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // SpringBoot2.4.0 [allowedOriginPatterns]代替[allowedOrigins]
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET","POST","PUT","DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").
                addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").
                addResourceLocations("classpath:/META-INF/resources/webjars/");
    }



}