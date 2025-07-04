package com.graphy.service.mapper;

import com.graphy.service.bean.dto.PlatformCodePageDto;
import com.graphy.service.bean.param.PlatformCodePageParam;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2021-07-22 00:47:45
 * @describe 字典管理
 */
@Repository("com.graphy.service.mapper.platformcodemapper")
public interface PlatformCodeMapper {

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 分页获取字典信息
     *
     * @param param 参数
     * @return
     */
    IPage<PlatformCodePageDto> getCodePage(Page<?> page, @Param("param") PlatformCodePageParam param) throws Exception;

}