package com.graphy.service.service;

import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformTaskPageParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbPlatformTaskEntity;

/**
 * @auther lsd
 * @create 2021-08-08 11:37:35
 * @describe 定时任务
 */
public interface PlatformTaskService extends IService<TbPlatformTaskEntity> {

    /**
     * auther： lsd
     * create： 2021-08-08 11:40:46
     * describe： 分页获取任务记录
     * @param param   参数
     * @return
     */
    OwnResult<OwnPageResult<TbPlatformTaskEntity>> getPlatformTaskPage(PlatformTaskPageParam param) throws Exception;

    /**
    * auther： lsd
    * create： 2021-08-08 11:43:19
    * describe： 保存定时任务
    * @param param   参数
    * @return
    */
    OwnResult<Boolean> savePlatformTask(TbPlatformTaskEntity param) throws Exception;
}