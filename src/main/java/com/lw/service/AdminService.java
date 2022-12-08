package com.lw.service;

import com.lw.mapper.AdminMapper;
import com.lw.model.dto.LoginDTO;
import com.lw.model.entity.AdminEntity;
import com.lw.model.vo.UserVo;
import com.lw.utils.ErrorCode;
import com.lw.utils.JwtUtil;
import com.lw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @project: Graduation-Project
 * @Description: 管理员服务类
 * @Author: 李伟
 * @Create: 2022/11/18 16:51
 * @Version: 1.0
 */

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Result<UserVo> login(LoginDTO user){
        AdminEntity currentUser = adminMapper.selectOneByUsername(user.getUsername());
        // 判断账号不为空
        if (currentUser == null || "".equals(currentUser.getUsername())){
            return Result.fail(ErrorCode.USER_NOT_EXIST);
        }
        // 验证密码是否正确
        if(!passwordEncoder.matches(user.getPassword(),currentUser.getPassword())){
            return Result.fail(ErrorCode.PASSWORD_WAS_INCORRECT);
        }
        // 颁发凭证
        String token = JwtUtil.generator(currentUser.getUsername());
        UserVo userVo = new UserVo();
        userVo.setUsername(currentUser.getUsername());
        userVo.setToken(token);
        return Result.success(userVo);
    }

    public Result<UserVo> register(AdminEntity adminEntity) {
        AdminEntity currentUser = adminMapper.selectOneByUsername(adminEntity.getUsername());
        // 判断账号是否存在
        if (currentUser != null){
            return Result.fail(ErrorCode.USER_ALREADY_EXISTS);
        }
        // 加密密码
        adminEntity.setPassword(passwordEncoder.encode(adminEntity.getPassword()));
        // 设置权限
        adminEntity.setRole("管理员");
        // 插入数据库
        adminMapper.insert(adminEntity);
        // 颁发凭证
        String token = JwtUtil.generator(adminEntity.getUsername());
        UserVo userVo = new UserVo();
        userVo.setUsername(adminEntity.getUsername());
        userVo.setToken(token);
        return Result.success(userVo);
    }
}
