package com.lw.mapper;

import com.lw.model.entity.GraduateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @project: Graduation-Project
 * @Description: 毕业生 Mapper 接口
 * @Author: 李伟
 * @Create: 2022/11/16 16:46
 * @Version: 1.0
 */

@Mapper
public interface GraduateMapper {


    /**
     * 根据学号查用户
     * @param username 学号
     * @return 毕业生实体
     */
    GraduateEntity selectOneByStudentId(@Param("studentId") String username);

    /**
     * 添加新用户
     * @param graduateEntity 需要注册的用户
     * @return 是否成功的结果
     */
    int insertOne(GraduateEntity graduateEntity);


    /**
     * 根据学号修改密码
     * @param username 学号
     * @param password 密码
     * @return 是否成功的结果
     */
    int updatePassword(String username, String password);
}
