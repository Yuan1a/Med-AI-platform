package com.graphy.service.mapper;
import com.graphy.service.bean.dto.QueryPatientRecordDto;
import com.graphy.service.bean.param.QueryPatientRecordParam;

import org.springframework.stereotype.Repository;
import java.util.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2022-03-01 15:55:16
 * @describe 患者选择
 */
@Repository("com.graphy.service.mapper.busquerypatientmapper")
public interface BusQueryPatientMapper {

    /**
    * auther： lsd
    * create： 2022-03-01 15:59:46
    * describe： 搜索患者记录
    * @param param   参数
    * @return
    */
    IPage<QueryPatientRecordDto> getQueryPatientRecord(Page<?> page, @Param("param") QueryPatientRecordParam param) throws Exception;

}