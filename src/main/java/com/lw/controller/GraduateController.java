package com.lw.controller;

import com.lw.model.dto.LoginDTO;
import com.lw.model.entity.GraduateEntity;
import com.lw.service.GraduateService;
import com.lw.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: Graduation-Project
 * @Description: 毕业生控制层
 * @Author: 李伟
 * @Create: 2022/11/16 16:13
 * @Version: 1.0
 */

@Api(tags = "毕业生模块")
@RestController
@RequestMapping("/graduate")
public class GraduateController {

    @Autowired
    private GraduateService graduateService;

    /**
     * <h2>毕业生登录</h2>
     * @param user 登录的对象
     * @return 通用响应结果集
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result<GraduateEntity> login(@RequestBody LoginDTO user){
        return graduateService.login(user);
    }

    /**
     * <h2>毕业生注册</h2>
     * @param entity 注册的对象
     * @return 通用响应结果集
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public Result<GraduateEntity> register(@RequestBody GraduateEntity entity){
        return graduateService.register(entity);
    }

    /**
     * <h2>毕业生修改密码</h2>
     * @param username 学号
     * @param password 密码
     * @return 结果集
     */
    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码")
    public Result updatePassword(String username,String password){
        return graduateService.updatePassword(username,password);
    }





}
