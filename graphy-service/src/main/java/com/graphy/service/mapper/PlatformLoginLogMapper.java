package com.graphy.service.mapper;
import com.graphy.service.bean.dto.PlatformLoginLogsDto;

import com.graphy.service.bean.param.PlatformLoginLogsParam;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2021-07-28 23:15:04
 * @describe 登录日志
 */
@Repository("com.graphy.service.mapper.platformloginlogmapper")
public interface PlatformLoginLogMapper {

    /**
    * auther： lsd
    * create： 2021-07-28 23:27:14
    * describe： 分页获取登录信息
    * @param param   参数
    * @return
    */
    IPage<PlatformLoginLogsDto> getLoginLogs(Page<?> page, @Param("param") PlatformLoginLogsParam param) throws Exception;

}