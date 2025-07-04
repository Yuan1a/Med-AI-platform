package com.graphy.service.service;
import com.graphy.service.bean.dto.PlatformLoginLogsDto;

import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformLoginLogsParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbPlatformLoginEntity;

/**
 * @auther lsd
 * @create 2021-07-28 23:15:04
 * @describe 登录日志
 */
public interface PlatformLoginLogService extends IService<TbPlatformLoginEntity> {

    /**
    * auther： lsd
    * create： 2021-07-28 23:27:14
    * describe： 分页获取登录信息
    * @param param   参数
    * @return
    */
    OwnResult<OwnPageResult<PlatformLoginLogsDto>> getLoginLogs(PlatformLoginLogsParam param) throws Exception;
}