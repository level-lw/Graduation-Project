package com.lw.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @project: Graduation-Project
 * @Description:
 * @Author: 李伟
 * @Create: 2022/11/17 8:57
 * @Version: 1.0
 */

@Data
@ApiModel(value = "LoginDto",description = "登录请求dto")
public class LoginDTO {

    @ApiModelProperty("登录名")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
