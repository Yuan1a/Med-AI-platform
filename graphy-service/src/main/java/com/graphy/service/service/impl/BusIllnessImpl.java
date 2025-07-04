package com.graphy.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.graphy.db.entity.TbBusDiagnoseEntity;
import com.graphy.service.bean.param.SaveIllnessRecordParam;
import com.graphy.service.bean.dto.IllnessRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.IllnessRecordParam;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.BusIllnessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbBusIllnessMapper;
import com.graphy.db.entity.TbBusIllnessEntity;
import com.graphy.service.service.BusIllnessService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;

/**
 * @auther lsd
 * @create 2022-03-01 11:21:35
 * @describe 病种管理
 */
@Service("com.graphy.service.service.impl.busillnessimpl")
@RequiredArgsConstructor
@Slf4j
public class BusIllnessImpl extends ServiceImpl<TbBusIllnessMapper, TbBusIllnessEntity> implements BusIllnessService {

    private final BusIllnessMapper busIllnessMapper;

    /**
     * auther： lsd
     * create： 2022-03-01 12:42:07
     * describe： 获取病种信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<OwnPageResult<IllnessRecordDto>> getIllnessRecord(IllnessRecordParam param) throws Exception {
        IPage<IllnessRecordDto> pageResult = busIllnessMapper.getIllnessRecord(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(pageResult.getTotal(), pageResult.getSize(), pageResult.getCurrent(), pageResult.getRecords()));
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:43:00
     * describe： 保存病种信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<Boolean> saveIllnessRecord(SaveIllnessRecordParam param) throws Exception {
//        if (StrUtil.hasEmpty(param.getType()))
//            return OwnResult.error("请设置病种类别");
        if (StrUtil.hasEmpty(param.getName()))
            return OwnResult.error("请设置病种名称");
        TbBusIllnessEntity orm = JSONObject.parseObject(JSONObject.toJSONString(param), TbBusIllnessEntity.class);
        if (StrUtil.hasEmpty(orm.getId())) {
            this.save(orm);
        } else {
            this.updateById(orm);
        }
        return OwnResult.result(true);
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:43:23
     * describe： 删除病种信息
     *
     * @param id 主键
     * @return
     */
    public OwnResult<Boolean> delIllnessRecord(String id) throws Exception {
        if (StrUtil.hasEmpty(id)) return OwnResult.error("请设置主键");
        TbBusIllnessEntity delOrm = new TbBusIllnessEntity();
        delOrm.setId(id);
        delOrm.setStatus("0");
        this.updateById(delOrm);
        return OwnResult.result(true);
    }

}