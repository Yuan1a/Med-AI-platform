package com.graphy.service.service;

import com.graphy.service.bean.dto.PlatformRolePowerDto;
import com.graphy.service.bean.param.PlatformRolePowerParam;

import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformRolePageParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbPlatformRoleEntity;

import java.util.*;

/**
 * @auther lsd
 * @create 2021-07-22 00:48:57
 * @describe 角色管理
 */
public interface PlatformRoleService extends IService<TbPlatformRoleEntity> {

    /**
     * auther： lsd
     * create： 2021-07-25 14:08:25
     * describe： 分页获取角色信息
     *
     * @param param 参数
     * @return
     */
    OwnResult<OwnPageResult<TbPlatformRoleEntity>> getRolePage(PlatformRolePageParam param) throws Exception;

    /**
     * auther： lsd
     * create： 2021-07-25 14:09:21
     * describe： 保存角色信息
     *
     * @param param 参数
     * @return
     */
    OwnResult<Boolean> saveRole(TbPlatformRoleEntity param) throws Exception;

    /**
     * auther： lsd
     * create： 2021-07-25 14:45:48
     * describe： 获取角色权限信息
     *
     * @param param 参数
     * @return
     */
    OwnResult<List<PlatformRolePowerDto>> getRolePower(PlatformRolePowerParam param) throws Exception;
}