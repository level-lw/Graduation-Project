package com.lw.mapper;

import com.lw.model.entity.AdminEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @project: Graduation-Project
 * @Description: 管理员 Mapper接口
 * @Author: 李伟
 * @Create: 2022/11/18 16:34
 * @Version: 1.0
 */

@Mapper
public interface AdminMapper {

    /**
     * <h2>根据用户名查用户</h2>
     * @param username 用户名
     * @return 管理员实体
     */
    AdminEntity selectOneByUsername(@Param("username") String username);
}
