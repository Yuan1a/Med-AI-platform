package com.graphy.service.mapper;

import com.graphy.service.bean.dto.PlatformIndexModuleDto;
import org.springframework.stereotype.Repository;
import java.util.*;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2021-07-22 00:45:16
 * @describe 平台首页
 */
@Repository("com.graphy.service.mapper.platformindexmapper")
public interface PlatformIndexMapper {

    /**
     * auther： lsd
     * create： 2021-07-27 08:34:30
     * describe： 获取用户导航权限
     *
     * @param userId 用户ID
     * @return
     */
    List<PlatformIndexModuleDto> getIndexModule(@Param("userId") String userId) throws Exception;

}