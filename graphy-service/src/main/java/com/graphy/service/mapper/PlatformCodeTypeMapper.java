package com.graphy.service.mapper;
import com.graphy.db.entity.TbPlatformCodeTypeEntity;
import com.graphy.service.bean.param.PlatformCodeTypePageParam;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2021-07-22 00:48:03
 * @describe 字典类别
 */
@Repository("com.graphy.service.mapper.platformcodetypemapper")
public interface PlatformCodeTypeMapper {

    /**
    * auther： lsd
    * create： 2021-07-24 13:16:36
    * describe： 分页获取字典类别信息
    * @param param   参数
    * @return
    */
    IPage<TbPlatformCodeTypeEntity> getCodeTypePage(Page<?> page, @Param("param") PlatformCodeTypePageParam param) throws Exception;

}