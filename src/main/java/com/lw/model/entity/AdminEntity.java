package com.lw.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @project: Graduation-Project
 * @Description: 管理员实体类
 * @Author: 李伟
 * @Create: 2022/11/18 16:26
 * @Version: 1.0
 */

@Data
@ApiModel(value = "Admin对象",description = "管理员表")
public class AdminEntity {

    @ApiModelProperty(value = "管理员ID")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
