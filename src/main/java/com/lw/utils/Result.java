package com.lw.utils;

import lombok.Data;

/**
 * @project: Graduation-Project
 * @Description: api请求结果统一封装对象
 * @Author: 李伟
 * @Create: 2022/11/17 9:57
 * @Version: 1.0
 */

@Data
public final class Result<T> {
    private static final String OPT_SUCCESS_STATUS = "complete";
    private static final String OPT_ERROR_STATUS = "error";

    /**
     * api执行结果状态：成功:complete 失败: error"
     */
    private String status;

    /**
     * 错误代码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 分页查询的总纪录数
     */
    private Long count;

    /**
     * 返回结果数据
     */
    private T resultData;



    public static <T> Result<T> success(T t) {
        return new Result<>("complete", (String)null, (String)null, 0L, t);
    }

    public static Result success() {
        return new Result<>("complete", (String)null, (String)null, 0L, (Object)null);
    }

    public static <T> Result<T> success(T t, Long count) {
        return new Result<>("complete", (String)null, (String)null, count, t);
    }

    public static <T> Result<T> successPage(T t, Long count) {
        return new Result<>("complete", (String)null, (String)null, count, t);
    }

    public static <T> Result<T> fail(String errorCode, String errorMessage) {
        return new Result("error", errorCode, errorMessage, 0L, (Object)null);
    }

    public static <T> Result<T> fail(String errorMessage) {
        return new Result("error", "001", errorMessage, 0L, (Object)null);
    }

    public static <T> Result<T> fail(EnumBase errorCode) {
        return fail(errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> Result<T> failList(EnumBase errorCode, T t) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage(), (String)null, 0L, t);
    }



    @Override
    public String toString() {
        return "Result{" +
                "status='" + status + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", count=" + count +
                ", resultData=" + resultData +
                '}';
    }

    public Result() {
    }

    public Result(String status, String errorCode, String errorMessage, Long count, T resultData) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.count = count;
        this.resultData = resultData;
    }
}
