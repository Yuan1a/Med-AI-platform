package com.graphy.service.mapper;

import com.graphy.service.bean.dto.PlatformUserPageDto;
import com.graphy.service.bean.param.PlatformUserPageParam;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2021-07-22 00:49:32
 * @describe 用户管理
 */
@Repository("com.graphy.service.mapper.platformusermapper")
public interface PlatformUserMapper {

    /**
     * auther： lsd
     * create： 2021-07-26 09:03:18
     * describe： 分页获取用户信息
     *
     * @param param 参数
     * @return
     */
    IPage<PlatformUserPageDto> getUserPage(Page<?> page, @Param("param") PlatformUserPageParam param) throws Exception;

}