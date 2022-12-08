package com.lw.controller;

import com.lw.model.dto.LoginDTO;
import com.lw.model.entity.AdminEntity;
import com.lw.model.vo.UserVo;
import com.lw.service.AdminService;
import com.lw.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: Graduation-Project
 * @Description: 管理员控制层
 * @Author: 李伟
 * @Create: 2022/11/18 16:21
 * @Version: 1.0
 */

@Api(tags = "管理员模块")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * <h2>管理员登录</h2>
     * @param loginDTO 登录的对象
     * @return 通用响应结果集
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<UserVo> login(@RequestBody LoginDTO loginDTO){
        return adminService.login(loginDTO);
    }

    /**
     * <h2>管理员注册</h2>
     * @param adminEntity 管理员实体类
     * @return 通用响应结果集
     */
    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public Result<UserVo> register(@RequestBody AdminEntity adminEntity){
        return adminService.register(adminEntity);
    }


}
