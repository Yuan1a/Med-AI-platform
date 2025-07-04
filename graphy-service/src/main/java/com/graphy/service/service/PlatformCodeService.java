package com.graphy.service.service;

import com.graphy.service.bean.dto.PlatformCodePageDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformCodePageParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbPlatformCodeEntity;

/**
 * @auther lsd
 * @create 2021-07-22 00:47:45
 * @describe 字典管理
 */
public interface PlatformCodeService extends IService<TbPlatformCodeEntity> {

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 分页获取字典信息
     *
     * @param param 参数
     * @return
     */
    OwnResult<OwnPageResult<PlatformCodePageDto>> getCodePage(PlatformCodePageParam param) throws Exception;

    /**
     * auther： lsd
     * create： 2021-07-23 23:53:58
     * describe： 保存字典信息
     *
     * @param param 参数
     * @return
     */
    OwnResult<Boolean> saveCode(TbPlatformCodeEntity param) throws Exception;
}