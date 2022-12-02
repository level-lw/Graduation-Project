package com.lw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @project: Graduation-Project
 * @Description: SpringBoot启动类
 * @Author: 李伟
 * @Create: 2022/11/16 16:10
 * @Version: 1.0
 */

@SpringBootApplication
@MapperScan(value = {"com.lw.mapper"})
@EnableSwagger2WebMvc
public class GraduationProjectApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(GraduationProjectApplication.class, args);
	}

	/**
	 * <h2>继承SpringBootServletInitializer 打war包,tomcat部署</h2>
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GraduationProjectApplication.class);
	}


}
