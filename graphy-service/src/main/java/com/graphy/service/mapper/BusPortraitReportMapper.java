package com.graphy.service.mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graphy.service.bean.dto.DiagnoseRecordDto;
import com.graphy.service.bean.dto.PortraitReportRecordDto;

import com.graphy.service.bean.param.DiagnoseRecordParam;
import com.graphy.service.bean.param.PortraitReportRecordParam;
import org.springframework.stereotype.Repository;
import java.util.*;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2022-03-01 11:22:46
 * @describe 影像报告
 */
@Repository("com.graphy.service.mapper.busportraitreportmapper")
public interface BusPortraitReportMapper {

    /**
    * auther： lsd
    * create： 2022-03-01 12:48:05
    * describe： 获取记录
    * @param
    */
    IPage<PortraitReportRecordDto> getPortraitReportRecord(Page<?> page, @Param("param") PortraitReportRecordParam param) throws Exception;

}