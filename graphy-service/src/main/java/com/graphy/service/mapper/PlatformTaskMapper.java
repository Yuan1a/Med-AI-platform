package com.graphy.service.mapper;
import com.graphy.db.entity.TbPlatformTaskEntity;
import com.graphy.service.bean.param.PlatformTaskPageParam;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2021-08-08 11:37:35
 * @describe 定时任务
 */
@Repository("com.graphy.service.mapper.platformtaskmapper")
public interface PlatformTaskMapper {

    /**
    * auther： lsd
    * create： 2021-08-08 11:40:46
    * describe： 分页获取任务记录
    * @param param   参数
    * @return
    */
    IPage<TbPlatformTaskEntity> getPlatformTaskPage(Page<?> page, @Param("param") PlatformTaskPageParam param) throws Exception;

}