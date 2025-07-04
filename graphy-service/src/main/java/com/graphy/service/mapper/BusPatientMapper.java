package com.graphy.service.mapper;
import com.graphy.service.bean.dto.PatientRecordDto;
import com.graphy.service.bean.param.PatientRecordParam;

import org.springframework.stereotype.Repository;
import java.util.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2022-03-01 11:22:06
 * @describe 患者管理
 */
@Repository("com.graphy.service.mapper.buspatientmapper")
public interface BusPatientMapper {

    /**
    * auther： lsd
    * create： 2022-03-01 11:54:13
    * describe： 获取患者信息
    * @param param   参数
    * @return
    */
    IPage<PatientRecordDto> getPatientRecord(Page<?> page, @Param("param") PatientRecordParam param) throws Exception;
    /**
     * auther： lsd
     * create： 2022-03-01 11:54:13
     * describe： 获取患者信息
     * @param param   参数
     * @return
     */
    IPage<PatientRecordDto> getPatientInfo(Page<?> page, @Param("param") PatientRecordParam param) throws Exception;
    /**
     * auther： lsd
     * create： 2022-03-01 11:54:13
     * describe： 获取患者信息
     * @param param   参数
     * @return
     */
    List<PatientRecordDto> getPatientRecord1( @Param("param") PatientRecordParam param) throws Exception;

}