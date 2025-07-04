package com.graphy.service.mapper;

import com.graphy.db.entity.TbPlatformDbUpEntity;
import com.graphy.service.bean.param.PlatformDbUpPageParam;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2021-08-07 20:24:54
 * @describe 数据日志
 */
@Repository("com.graphy.service.mapper.platformdbupmapper")
public interface PlatformDbUpMapper {

    /**
     * auther： lsd
     * create： 2021-08-07 23:08:32
     * describe： 分页获取记录
     *
     * @param param 参数
     * @return
     */
    IPage<TbPlatformDbUpEntity> getPlatformDbUpPage(Page<?> page, @Param("param") PlatformDbUpPageParam param) throws Exception;

    /**
     * 清除过期数据
     *
     * @param counts
     * @return
     * @throws Exception
     */
    Boolean cleanData() throws Exception;
}