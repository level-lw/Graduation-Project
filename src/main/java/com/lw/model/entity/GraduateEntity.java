package com.lw.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @project: Graduation-Project
 * @Description: 毕业生实体类
 * @Author: 李伟
 * @Create: 2022/11/16 16:26
 * @Version: 1.0
 */

@Data
@ApiModel(value = "Graduate对象",description = "毕业生表")
public class GraduateEntity {

    @ApiModelProperty(value = "毕业生ID")
    private String id;

    @ApiModelProperty(value = "毕业生学号")
    private String studentId;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "院系")
    private String faculty;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
