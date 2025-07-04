package com.graphy.service.mapper;
import com.graphy.db.entity.TbPlatformLoginErrorEntity;
import com.graphy.service.bean.param.PlatformLoginErrorsParam;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2021-07-29 21:25:29
 * @describe 登录错误
 */
@Repository("com.graphy.service.mapper.platformloginerrormapper")
public interface PlatformLoginErrorMapper {

    /**
    * auther： lsd
    * create： 2021-07-29 21:30:09
    * describe： 分页获取登录错误日志
    * @param param   参数
    * @return
    */
    IPage<TbPlatformLoginErrorEntity> getLoginErrors(Page<?> page, @Param("param") PlatformLoginErrorsParam param) throws Exception;

}