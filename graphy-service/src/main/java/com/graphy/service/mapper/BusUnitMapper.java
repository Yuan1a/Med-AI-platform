package com.graphy.service.mapper;
import com.graphy.service.bean.dto.UnitRecordDto;
import com.graphy.service.bean.param.UnitRecordParam;

import org.springframework.stereotype.Repository;
import java.util.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2022-03-01 11:21:01
 * @describe 机构管理
 */
@Repository("com.graphy.service.mapper.busunitmapper")
public interface BusUnitMapper {

    /**
    * auther： lsd
    * create： 2022-03-01 11:48:53
    * describe： 获取机构信息列表
    * @param param   参数
    * @return
    */
    IPage<UnitRecordDto> getUnitRecord(Page<?> page, @Param("param") UnitRecordParam param) throws Exception;

}