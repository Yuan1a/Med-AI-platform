package com.graphy.service.mapper;

import com.graphy.service.bean.dto.PlatformRolePowerDto;
import com.graphy.service.bean.param.PlatformRolePowerParam;

import com.graphy.db.entity.TbPlatformRoleEntity;
import com.graphy.service.bean.param.PlatformRolePageParam;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * @auther lsd
 * @create 2021-07-22 00:48:57
 * @describe 角色管理
 */
@Repository("com.graphy.service.mapper.platformrolemapper")
public interface PlatformRoleMapper {

    /**
     * auther： lsd
     * create： 2021-07-25 14:08:25
     * describe： 分页获取角色信息
     *
     * @param param 参数
     * @return
     */
    IPage<TbPlatformRoleEntity> getRolePage(Page<?> page, @Param("param") PlatformRolePageParam param) throws Exception;

    /**
     * auther： lsd
     * create： 2021-07-25 14:45:48
     * describe： 获取角色权限信息
     *
     * @param param 参数
     * @return
     */
    List<PlatformRolePowerDto> getRolePower(@Param("param") PlatformRolePowerParam param) throws Exception;

}