package com.graphy.service.mapper;
import com.graphy.service.bean.dto.PlatformTaskLogDto;
import com.graphy.service.bean.param.PlatformTaskLogParam;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @auther lsd
 * @create 2021-08-08 23:26:38
 * @describe 任务日志
 */
@Repository("com.graphy.service.mapper.platformtasklogmapper")
public interface PlatformTaskLogMapper {

    /**
     * 清除过期数据
     *
     * @return
     * @throws Exception
     */
    Boolean cleanData() throws Exception;

    /**
    * auther： lsd
    * create： 2021-08-09 22:40:25
    * describe： 分页获取任务执行日志
    * @param param   参数
    * @return
    */
    IPage<PlatformTaskLogDto> getPlatformTaskLog(Page<?> page, @Param("param") PlatformTaskLogParam param) throws Exception;

}