package com.graphy.service.mapper;

import com.graphy.db.entity.TbPlatformRequestEntity;
import com.graphy.service.bean.param.PlatformRequestPageParam;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2021-08-07 10:44:53
 * @describe 网络请求监听
 */
@Repository("com.graphy.service.mapper.platformrequestmapper")
public interface PlatformRequestMapper {

    /**
     * auther： lsd
     * create： 2021-08-07 23:11:13
     * describe： 分页获取数据
     *
     * @param param 参数
     * @return
     */
    IPage<TbPlatformRequestEntity> getPlatformRequestPage(Page<?> page, @Param("param") PlatformRequestPageParam param) throws Exception;

    /**
     * 清除过期数据
     *
     * @param counts
     * @return
     * @throws Exception
     */
    Boolean cleanData(@Param("counts") Integer counts) throws Exception;
}