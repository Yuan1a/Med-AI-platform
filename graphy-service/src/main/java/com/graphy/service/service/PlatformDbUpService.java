package com.graphy.service.service;

import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformDbUpPageParam;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbPlatformDbUpEntity;

/**
 * @auther lsd
 * @create 2021-08-07 20:24:54
 * @describe 数据日志
 */
public interface PlatformDbUpService extends IService<TbPlatformDbUpEntity> {

    /**
     * auther： lsd
     * create： 2021-08-07 23:08:32
     * describe： 分页获取记录
     *
     * @param param 参数
     * @return
     */
    OwnResult<OwnPageResult<TbPlatformDbUpEntity>> getPlatformDbUpPage(PlatformDbUpPageParam param) throws Exception;

    /**
     * 清除过期数据
     *
     * @throws Exception
     */
    OwnResult<Boolean> cleanData() throws Exception;
}