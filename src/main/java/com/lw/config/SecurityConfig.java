package com.lw.config;

import com.lw.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @project: Graduation-Project
 * @Description: Security配置类  启用全局方法权限控制
 * @Author: 李伟
 * @Create: 2022-11-29 23:48
 * @Version: 1.0
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    private JwtAuthenticationEntry authenticationEntry;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置security的过滤器链
        http.csrf().disable()
                //  filters
                // ajax: header加东西  options  ----  get
                .cors()  // cors
                .and()
                // 禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/login", "/admin/register")
                .permitAll()
                .antMatchers("/doc.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // 全局认证异常处理  ----》 AuthenticationException
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntry)
                .and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }



    /**
     * 因为Security默认提供的统一认证过滤器只能对Session模式有效
     * @return 所以我们需要自定义针对于token的 统一认证过滤器
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}