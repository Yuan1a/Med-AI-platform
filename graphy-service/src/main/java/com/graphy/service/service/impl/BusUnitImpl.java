package com.graphy.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.db.entity.TbBusIllnessEntity;
import com.graphy.db.entity.TbBusPortraitReportEntity;
import com.graphy.db.entity.TbBusRegionEntity;
import com.graphy.db.service.TbBusRegionService;
import com.graphy.service.bean.param.SaveUnitRecordParam;
import com.graphy.service.bean.dto.UnitRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.UnitRecordParam;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.BusUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbBusUnitMapper;
import com.graphy.db.entity.TbBusUnitEntity;
import com.graphy.service.service.BusUnitService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;

/**
 * @auther lsd
 * @create 2022-03-01 11:21:01
 * @describe 机构管理
 */
@Service("com.graphy.service.service.impl.busunitimpl")
@RequiredArgsConstructor
@Slf4j
public class BusUnitImpl extends ServiceImpl<TbBusUnitMapper, TbBusUnitEntity> implements BusUnitService {

    private final BusUnitMapper busUnitMapper;
    private final TbBusRegionService tbBusRegionService;

    /**
     * auther： lsd
     * create： 2022-03-01 11:48:53
     * describe： 获取机构信息列表
     *
     * @param param 参数
     * @return
     */
    public OwnResult<OwnPageResult<UnitRecordDto>> getUnitRecord(UnitRecordParam param) throws Exception {
        IPage<UnitRecordDto> pageResult = busUnitMapper.getUnitRecord(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(pageResult.getTotal(), pageResult.getSize(), pageResult.getCurrent(), pageResult.getRecords()));
    }

    /**
     * auther： lsd
     * create： 2022-03-01 11:50:53
     * describe： 保存机构信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<Boolean> saveUnitRecord(SaveUnitRecordParam param) throws Exception {
        if (StrUtil.hasEmpty(param.getCode()))
            return OwnResult.error("请设置编码");
        if (StrUtil.hasEmpty(param.getName()))
            return OwnResult.error("请设置名称");

        if (!StrUtil.hasEmpty(param.getMobile())) {
            if (param.getMobile().length() != 11 || !param.getMobile().subSequence(0, 1).equals("1")) return OwnResult.error("联系手机格式错误");
        }

        TbBusUnitEntity orm = JSONObject.parseObject(JSONObject.toJSONString(param), TbBusUnitEntity.class);

        String address = "";

        if (!StrUtil.hasEmpty(orm.getProv())) {
            address += tbBusRegionService.getOne(new LambdaQueryWrapper<TbBusRegionEntity>().eq(TbBusRegionEntity::getCode, orm.getProv())).getName();
        }
        if (!StrUtil.hasEmpty(orm.getCity())) {
            address += tbBusRegionService.getOne(new LambdaQueryWrapper<TbBusRegionEntity>().eq(TbBusRegionEntity::getCode, orm.getCity())).getName();
        }
        if (!StrUtil.hasEmpty(orm.getCounty())) {
            address += tbBusRegionService.getOne(new LambdaQueryWrapper<TbBusRegionEntity>().eq(TbBusRegionEntity::getCode, orm.getCounty())).getName();
        }
        if (!StrUtil.hasEmpty(orm.getTown())) {
            address += tbBusRegionService.getOne(new LambdaQueryWrapper<TbBusRegionEntity>().eq(TbBusRegionEntity::getCode, orm.getTown())).getName();
        }
        if (!StrUtil.hasEmpty(orm.getAddr())) {
            address += orm.getAddr();
        }
        orm.setAddress(address);


        if (StrUtil.hasEmpty(orm.getId())) {
            this.save(orm);
        } else {
            this.updateById(orm);
        }
        return OwnResult.result(true);
    }

    /**
     * auther： lsd
     * create： 2022-03-01 11:51:17
     * describe： 删除机构信息
     *
     * @param id 主键
     * @return
     */
    public OwnResult<Boolean> delUnitRecord(String id) throws Exception {
        if (StrUtil.hasEmpty(id)) return OwnResult.error("请设置主键");
        TbBusUnitEntity delOrm = new TbBusUnitEntity();
        delOrm.setId(id);
        delOrm.setStatus("0");
        this.updateById(delOrm);
        return OwnResult.result(true);
    }

}