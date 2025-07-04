package com.graphy.service.service;

import com.graphy.service.bean.param.SaveDiagnoseRecordParam;
import com.graphy.service.bean.dto.DiagnoseRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.DiagnoseRecordParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbBusDiagnoseEntity;
import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 11:23:17
 * @describe 诊断信息
 */
public interface BusDiagnoseService extends IService<TbBusDiagnoseEntity> {

    /**
     * auther： lsd
     * create： 2022-03-01 12:52:43
     * describe： 获取记录
     * @param param   参数
     * @return
     */
    OwnResult<OwnPageResult<DiagnoseRecordDto>> getDiagnoseRecord(DiagnoseRecordParam param) throws Exception;

    /**
     * auther： lsd
     * create： 2022-03-01 12:53:36
     * describe： 保存信息
     * @param param   请求参数
     * @return
     */
    OwnResult<Boolean> saveDiagnoseRecord(SaveDiagnoseRecordParam param) throws Exception;

    /**
    * auther： lsd
    * create： 2022-03-01 12:53:56
    * describe： 删除诊断信息
    * @param id   主键
    * @return
    */
    OwnResult<Boolean> delDiagnoseRecord(String id) throws Exception;
}