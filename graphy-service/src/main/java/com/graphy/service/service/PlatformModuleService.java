package com.graphy.service.service;

import com.graphy.service.bean.dto.PlatformModuleListDto;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformModuleListParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbPlatformModuleEntity;

import java.util.*;

/**
 * @auther lsd
 * @create 2021-07-22 00:48:24
 * @describe 页面导航
 */
public interface PlatformModuleService extends IService<TbPlatformModuleEntity> {

    /**
     * auther： lsd
     * create： 2021-07-24 17:04:19
     * describe： 获取导航列表数据
     *
     * @param param 参数
     * @return
     */
    OwnResult<List<PlatformModuleListDto>> getModuleList(PlatformModuleListParam param) throws Exception;

    /**
     * auther： lsd
     * create： 2021-07-24 23:05:20
     * describe： 保存导航信息
     *
     * @param param 参数
     * @return
     */
    OwnResult<Boolean> saveModule(TbPlatformModuleEntity param) throws Exception;
}