package com.graphy.service.service;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformLoginErrorsParam;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbPlatformLoginErrorEntity;

/**
 * @auther lsd
 * @create 2021-07-29 21:25:29
 * @describe 登录错误
 */
public interface PlatformLoginErrorService extends IService<TbPlatformLoginErrorEntity> {

    /**
    * auther： lsd
    * create： 2021-07-29 21:30:09
    * describe： 分页获取登录错误日志
    * @param param   参数
    * @return
    */
    OwnResult<OwnPageResult<TbPlatformLoginErrorEntity>> getLoginErrors(PlatformLoginErrorsParam param) throws Exception;
}