package com.graphy.service.service.impl;

import com.graphy.service.bean.dto.PlatformRolePowerDto;
import com.graphy.service.bean.param.PlatformRolePowerParam;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformRolePageParam;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbPlatformRoleMapper;
import com.graphy.db.entity.TbPlatformRoleEntity;
import com.graphy.service.service.PlatformRoleService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;

import java.util.*;

/**
 * @auther lsd
 * @create 2021-07-22 00:48:57
 * @describe 角色管理
 */
@Service("com.graphy.service.service.impl.platformroleimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformRoleImpl extends ServiceImpl<TbPlatformRoleMapper, TbPlatformRoleEntity> implements PlatformRoleService {

    private final PlatformRoleMapper platformRoleMapper;

    /**
     * auther： lsd
     * create： 2021-07-25 14:08:25
     * describe： 分页获取角色信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<OwnPageResult<TbPlatformRoleEntity>> getRolePage(PlatformRolePageParam param) throws Exception {
        IPage<TbPlatformRoleEntity> page = platformRoleMapper.getRolePage(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    /**
     * auther： lsd
     * create： 2021-07-25 14:09:21
     * describe： 保存角色信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<Boolean> saveRole(TbPlatformRoleEntity param) throws Exception {
        if (StrUtil.hasEmpty(param.getName()))
            return OwnResult.error("请设置角色名称");
        Boolean ok = false;
        /*此处编写您的逻辑代码*/
        if (StrUtil.hasEmpty(param.getId())) {
            param.setForbidDel("0");

            ok = this.save(param);
        } else {
            ok = this.update(param, new LambdaQueryWrapper<TbPlatformRoleEntity>().eq(TbPlatformRoleEntity::getId, param.getId()));
        }
        return OwnResult.result(ok);
    }

    /**
     * auther： lsd
     * create： 2021-07-25 14:45:48
     * describe： 获取角色权限信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<List<PlatformRolePowerDto>> getRolePower(PlatformRolePowerParam param) throws Exception {
        List<PlatformRolePowerDto> list = platformRoleMapper.getRolePower(param);
        return OwnResult.result(list);
    }

}