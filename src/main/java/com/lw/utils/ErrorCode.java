package com.lw.utils;

/**
 * @project: Graduation-Project
 * @Description: 错误代码枚举
 * @Author: 李伟
 * @Create: 2022/11/17 10:19
 * @Version: 1.0
 */
public enum ErrorCode implements EnumBase{
    // 登录用户不存在
    USER_NOT_EXIST("user_not_exist","用户不存在"),
    // 登录密码错误
    PASSWORD_WAS_INCORRECT("password_was_incorrect","密码不正确"),
    // 用户已存在
    USER_ALREADY_EXISTS("user_already_exists","注册用户已存在"),
    // 注册用户失败
    REGISTRATION_FAILED("registration_failed","注册用户失败"),
    // 更新密码失败
    UPDATE_PASSWORD_FAILED("update_password_failed","更新密码失败" );




    private final String code;

    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
