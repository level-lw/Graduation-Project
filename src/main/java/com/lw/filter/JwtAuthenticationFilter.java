package com.lw.filter;

import com.lw.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

/**
 * @program: Graduation-Project
 * @description: 过滤器
 * @author: LW
 * @create: 2022-12-02 23:38
 **/

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 基于JWT的认证的过滤器
        // 1.放行一些不需要认证的请求
        if (request.getRequestURI().equalsIgnoreCase("/admin/login")
                || request.getRequestURI().equalsIgnoreCase("/admin/register")) {
            filterChain.doFilter(request, response);
            return;
        }
        // options请求 试探性请求
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
            return;
        }

        // 放行swagger
        if (request.getRequestURI().equalsIgnoreCase("/doc.html")
                || request.getRequestURI().contains("/webjars")
                || request.getRequestURI().contains("/swagger-resources")
                || request.getRequestURI().contains("/v2/api-docs")) {
            System.out.println("放行swagger");
            filterChain.doFilter(request, response);
            return;
        }

        // 解析token
        String token = request.getHeader("Authorization");
        log.info("token: [{}]", token);
        Claims claims = JwtUtil.parse(token);
        if (claims == null) {
            throw new BadCredentialsException("解析失败，凭证可能被篡改");
        }

        if (claims.getExpiration().before(new Date())) {
            throw new BadCredentialsException("凭证已过期");
        }

        // 存入上下文
        String username = claims.getSubject();
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        username,null, Collections.emptyList()
                )
        );

        filterChain.doFilter(request, response);
    }
}