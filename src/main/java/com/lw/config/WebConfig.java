package com.lw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

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
                        // 针对的origin域名
                        .allowedOriginPatterns("*")
//                        .allowedOrigins("*")
                        // 针对的请求方法
                        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                        // 针对的请求头
                        .allowedHeaders("*")
                        // 是否允许携带cookie
                        .allowCredentials(true)
                        // 预检请求的有效期，单位为秒
                        .maxAge(3600);
            }
        };
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/**"}).addResourceLocations(new String[]{"classpath:/static/"});
        registry.addResourceHandler(new String[]{"swagger-ui.html"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/"});
        registry.addResourceHandler(new String[]{"doc.html"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/"});
        registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"});
        super.addResourceHandlers(registry);
    }

}