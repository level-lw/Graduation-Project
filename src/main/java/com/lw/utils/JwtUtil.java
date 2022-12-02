package com.lw.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Date;

/**
 * @program: Graduation-Project
 * @description: Jwt工具类
 * @author: LW
 * @create: 2022-11-29 23:43
 **/

public class JwtUtil {

    /**
     * 为了保证加密后字符串的安全性，声明一个密钥
     */
    private final static String SECRET ="LIWEI";

    /**
     * 定义过期时间 2小时
     */
    private final static Duration EXP_TIME = Duration.ofHours(2);

    /**
     * 根据用户名构建JWT字符串
     * @param username 用户名
     * @return JWT字符串
     */
    public static String generator(String username){
        // 定义过期时间
        Date expDate = new Date(System.currentTimeMillis()+EXP_TIME.toMillis());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    /**
     * 解析JWT字符串为Claims对象
     * @param token token
     * @return Claims对象
     */
    public static Claims parse(String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        // jwt的payload部分，jwt中数据都存放在Claims中
        Claims claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception ex){
            System.out.println("解析失败!");
        }
        return claims;
    }

}