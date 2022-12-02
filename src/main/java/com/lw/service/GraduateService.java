package com.lw.service;

import com.lw.mapper.GraduateMapper;
import com.lw.model.dto.LoginDTO;
import com.lw.model.entity.GraduateEntity;
import com.lw.utils.ErrorCode;
import com.lw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Objects;

/**
 * @project: Graduation-Project
 * @Description: 毕业生服务类
 * @Author: 李伟
 * @Create: 2022/11/16 16:55
 * @Version: 1.0
 */

@Service
public class GraduateService {

    @Autowired
    private GraduateMapper graduateMapper;

    public Result<GraduateEntity> login(LoginDTO user){
        GraduateEntity currentUser = graduateMapper.selectOneByStudentId(user.getUsername());
        // 判断账号不为空
        if (currentUser == null || "".equals(currentUser.getName())){
            return Result.fail(ErrorCode.USER_NOT_EXIST);
        }
        // 验证密码是否正确
        if (!Objects.equals(currentUser.getPassword(), user.getPassword())){
            return Result.fail(ErrorCode.PASSWORD_WAS_INCORRECT);
        }
        return Result.success(currentUser);
    }


    public Result<GraduateEntity> register(GraduateEntity entity){
        GraduateEntity user = graduateMapper.selectOneByStudentId(entity.getStudentId());
        // 判断用户是否已存在
        if (user != null){
            return Result.fail(ErrorCode.USER_ALREADY_EXISTS);
        }
        // 获取当前系统时间 作为id
        long time = System.currentTimeMillis();
        entity.setId(String.valueOf(time));
        // 插入新用户
        int i = graduateMapper.insertOne(entity);
        if (i<=0){
            return Result.fail(ErrorCode.REGISTRATION_FAILED);
        } else {
            return Result.success(entity);
        }

    }

    public Result updatePassword(String username, String password) {
        GraduateEntity user = graduateMapper.selectOneByStudentId(username);
        // 判断用户是否已存在
        if (user == null){
            return Result.fail(ErrorCode.USER_NOT_EXIST);
        }
        int i = graduateMapper.updatePassword(username, password);
        // 判断是否更新成功
        if (i<=0){
            return Result.fail(ErrorCode.UPDATE_PASSWORD_FAILED);
        } else {
            return Result.success("修改成功");
        }
    }
}
