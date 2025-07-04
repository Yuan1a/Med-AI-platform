package com.graphy.service.service;

import com.graphy.service.bean.param.SaveUnitRecordParam;
import com.graphy.service.bean.dto.UnitRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.UnitRecordParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbBusUnitEntity;
import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 11:21:01
 * @describe 机构管理
 */
public interface BusUnitService extends IService<TbBusUnitEntity> {

    /**
     * auther： lsd
     * create： 2022-03-01 11:48:53
     * describe： 获取机构信息列表
     * @param param   参数
     * @return
     */
    OwnResult<OwnPageResult<UnitRecordDto>> getUnitRecord(UnitRecordParam param) throws Exception;

    /**
     * auther： lsd
     * create： 2022-03-01 11:50:52
     * describe： 保存机构信息
     * @param param   请求参数
     * @return
     */
    OwnResult<Boolean> saveUnitRecord(SaveUnitRecordParam param) throws Exception;

    /**
    * auther： lsd
    * create： 2022-03-01 11:51:16
    * describe： 删除机构信息
    * @param id   主键
    * @return
    */
    OwnResult<Boolean> delUnitRecord(String id) throws Exception;
}