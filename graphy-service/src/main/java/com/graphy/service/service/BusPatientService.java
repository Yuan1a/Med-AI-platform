package com.graphy.service.service;
import com.graphy.service.bean.dto.NameDto;
import com.graphy.service.bean.param.NameParam;

import com.graphy.service.bean.param.SavePatientRecordParam;
import com.graphy.service.bean.dto.PatientRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PatientRecordParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbBusPatientEntity;
import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 11:22:06
 * @describe 患者管理
 */
public interface BusPatientService extends IService<TbBusPatientEntity> {

    /**
     * auther： lsd
     * create： 2022-03-01 11:54:13
     * describe： 获取患者信息
     * @param param   参数
     * @return
     */
    OwnResult<OwnPageResult<PatientRecordDto>> getPatientRecord(PatientRecordParam param) throws Exception;

    /**
     * auther： lsd
     * create： 2022-03-01 11:54:46
     * describe： 保存患者信息
     * @param param   请求参数
     * @return
     */
    OwnResult<Boolean> savePatientRecord(SavePatientRecordParam param) throws Exception;

    /**
     * auther： lsd
     * create： 2022-03-01 11:55:09
     * describe： 删除患者信息
     * @param id   主键
     * @return
     */
    OwnResult<Boolean> delPatientRecord(String id) throws Exception;
}