package com.lw.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @program: Graduation-Project
 * @description: security全局异常处理
 * @author: LW
 * @create: 2022-12-08 23:32
 **/

@Component
public class JwtAuthenticationEntry implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        HashMap<String,String> map = new HashMap<>(16);
        map.put("code", "403");
        map.put("message", authException.getMessage());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(map));
        response.getWriter().flush();
        response.getWriter().close();
    }
}