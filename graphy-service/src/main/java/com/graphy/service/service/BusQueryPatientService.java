package com.graphy.service.service;
import com.graphy.service.bean.dto.QueryPatientRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.QueryPatientRecordParam;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbBusPatientEntity;
import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 15:55:16
 * @describe 患者选择
 */
public interface BusQueryPatientService extends IService<TbBusPatientEntity> {

    /**
    * auther： lsd
    * create： 2022-03-01 15:59:46
    * describe： 搜索患者记录
    * @param param   参数
    * @return
    */
    OwnResult<OwnPageResult<QueryPatientRecordDto>> getQueryPatientRecord(QueryPatientRecordParam param) throws Exception;
}