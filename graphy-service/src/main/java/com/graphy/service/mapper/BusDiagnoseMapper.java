package com.graphy.service.mapper;
import com.graphy.service.bean.dto.DiagnoseRecordDto;
import com.graphy.service.bean.param.DiagnoseRecordParam;

import org.springframework.stereotype.Repository;
import java.util.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2022-03-01 11:23:17
 * @describe 诊断信息
 */
@Repository("com.graphy.service.mapper.busdiagnosemapper")
public interface BusDiagnoseMapper {

    /**
    * auther： lsd
    * create： 2022-03-01 12:52:43
    * describe： 获取记录
    * @param param   参数
    * @return
    */
    IPage<DiagnoseRecordDto> getDiagnoseRecord(Page<?> page, @Param("param") DiagnoseRecordParam param) throws Exception;

}