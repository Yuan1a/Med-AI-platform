package com.graphy.service.service;

import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformSaveConfigParam;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbPlatformConfigEntity;

/**
 * @auther lsd
 * @create 2021-08-06 22:56:24
 * @describe 高级配置
 */
public interface PlatformConfigService extends IService<TbPlatformConfigEntity> {

    /**
     * auther： lsd
     * create： 2021-08-06 23:11:10
     * describe： 保存配置信息
     *
     * @param param 参数
     * @return
     */
    OwnResult<Boolean> savePlatformConfig(PlatformSaveConfigParam param) throws Exception;
}