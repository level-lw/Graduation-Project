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
        if (request.getRequestURI().equals("/doc.html")||
                request.getRequestURI().equals("/swagger-ui.html")||
                request.getRequestURI().equals("/swagger-resources/**")||
                request.getRequestURI().equals("/webjars/**")||
                request.getRequestURI().equals("/v2/**")||
                request.getRequestURI().equals("/api/**")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");
        log.info("token: [{}]", token);
        Claims claims = JwtUtil.parse(token);
        if (claims == null) {
            throw new BadCredentialsException("解析失败，凭证可能被篡改");
        }

        if (claims.getExpiration().before(new Date())) {
            throw new BadCredentialsException("凭证已过期");
        }

        String username = claims.getSubject();
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        username,null, Collections.emptyList()
                )
        );

        filterChain.doFilter(request, response);
    }
}