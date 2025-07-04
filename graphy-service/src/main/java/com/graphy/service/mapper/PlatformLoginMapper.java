package com.graphy.service.mapper;

import com.graphy.service.bean.dto.PlatformUserDto;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2021-07-22 00:13:07
 * @describe 登录管理
 */
public interface PlatformLoginMapper {

    /**
     * auther： lsd
     * create： 2021-07-25 14:45:48
     * describe： 登录系统
     *
     * @param userAccount  账户
     * @param userPassword 密码
     * @return
     */
    PlatformUserDto login(@Param("userAccount") String userAccount, @Param("userPassword") String userPassword) throws Exception;


}