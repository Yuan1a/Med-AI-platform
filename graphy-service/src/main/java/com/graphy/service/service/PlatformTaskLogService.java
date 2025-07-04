package com.graphy.service.service;
import com.graphy.service.bean.dto.PlatformTaskLogDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.param.PlatformTaskLogParam;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbPlatformTaskLogEntity;
import com.graphy.service.bean.OwnResult;

/**
 * @auther lsd
 * @create 2021-08-08 23:26:38
 * @describe 任务日志
 */
public interface PlatformTaskLogService extends IService<TbPlatformTaskLogEntity> {

    /**
     * 清除过期数据
     *
     * @throws Exception
     */
    OwnResult<Boolean> cleanData() throws Exception;

    /**
    * auther： lsd
    * create： 2021-08-09 22:40:25
    * describe： 分页获取任务执行日志
    * @param param   参数
    * @return
    */
    OwnResult<OwnPageResult<PlatformTaskLogDto>> getPlatformTaskLog(PlatformTaskLogParam param) throws Exception;
}