package com.graphy.service.service;

import com.graphy.service.bean.dto.PlatformUserPageDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformUserPageParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbPlatformUserEntity;

/**
 * @auther lsd
 * @create 2021-07-22 00:49:32
 * @describe 用户管理
 */
public interface PlatformUserService extends IService<TbPlatformUserEntity> {

    /**
     * auther： lsd
     * create： 2021-07-26 09:03:18
     * describe： 分页获取用户信息
     *
     * @param param 参数
     * @return
     */
    OwnResult<OwnPageResult<PlatformUserPageDto>> getUserPage(PlatformUserPageParam param) throws Exception;

    /**
     * auther： lsd
     * create： 2021-07-26 10:53:50
     * describe： 保存用户信息
     *
     * @param param 参数
     * @return
     */
    OwnResult<Boolean> saveUser(TbPlatformUserEntity param) throws Exception;
}