package com.graphy.service.mapper;
import com.graphy.service.bean.dto.PlatformModuleListDto;
import com.graphy.service.bean.param.PlatformModuleListParam;

import org.springframework.stereotype.Repository;
import java.util.*;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2021-07-22 00:48:24
 * @describe 页面导航
 */
@Repository("com.graphy.service.mapper.platformmodulemapper")
public interface PlatformModuleMapper {

    /**
    * auther： lsd
    * create： 2021-07-24 17:04:19
    * describe： 获取导航列表数据
    * @param param   参数
    * @return
    */
    List<PlatformModuleListDto> getModuleList(@Param("param") PlatformModuleListParam param) throws Exception;

}