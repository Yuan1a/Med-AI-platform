package com.graphy.service.service;

import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformCodeTypePageParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbPlatformCodeTypeEntity;

/**
 * @auther lsd
 * @create 2021-07-22 00:48:03
 * @describe 字典类别
 */
public interface PlatformCodeTypeService extends IService<TbPlatformCodeTypeEntity> {

    /**
     * auther： lsd
     * create： 2021-07-24 13:16:36
     * describe： 分页获取字典类别信息
     *
     * @param param 参数
     * @return
     */
    OwnResult<OwnPageResult<TbPlatformCodeTypeEntity>> getCodeTypePage(PlatformCodeTypePageParam param) throws Exception;

    /**
     * auther： lsd
     * create： 2021-07-24 13:20:39
     * describe： 保存字典类别信息
     *
     * @param param 参数
     * @return
     */
    OwnResult<Boolean> saveCodeType(TbPlatformCodeTypeEntity param) throws Exception;
}