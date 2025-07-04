package com.graphy.service.service;

import com.graphy.service.bean.param.SaveIllnessRecordParam;
import com.graphy.service.bean.dto.IllnessRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.IllnessRecordParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbBusIllnessEntity;
import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 11:21:35
 * @describe 病种管理
 */
public interface BusIllnessService extends IService<TbBusIllnessEntity> {

    /**
     * auther： lsd
     * create： 2022-03-01 12:42:07
     * describe： 获取病种信息
     * @param param   参数
     * @return
     */
    OwnResult<OwnPageResult<IllnessRecordDto>> getIllnessRecord(IllnessRecordParam param) throws Exception;

    /**
     * auther： lsd
     * create： 2022-03-01 12:43:00
     * describe： 保存病种信息
     * @param param   请求参数
     * @return
     */
    OwnResult<Boolean> saveIllnessRecord(SaveIllnessRecordParam param) throws Exception;

    /**
    * auther： lsd
    * create： 2022-03-01 12:43:23
    * describe： 删除病种信息
    * @param id   主键
    * @return
    */
    OwnResult<Boolean> delIllnessRecord(String id) throws Exception;
}