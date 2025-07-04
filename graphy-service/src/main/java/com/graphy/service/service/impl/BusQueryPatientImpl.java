package com.graphy.service.service.impl;
import com.graphy.service.bean.dto.QueryPatientRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.QueryPatientRecordParam;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.BusQueryPatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbBusPatientMapper;
import com.graphy.db.entity.TbBusPatientEntity;
import com.graphy.service.service.BusQueryPatientService;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;

/**
 * @auther lsd
 * @create 2022-03-01 15:55:16
 * @describe 患者选择
 */
@Service("com.graphy.service.service.impl.busquerypatientimpl")
@RequiredArgsConstructor
@Slf4j
public class BusQueryPatientImpl extends ServiceImpl<TbBusPatientMapper, TbBusPatientEntity> implements BusQueryPatientService {

    private final BusQueryPatientMapper busQueryPatientMapper;

    /**
    * auther： lsd
    * create： 2022-03-01 15:59:46
    * describe： 搜索患者记录
    * @param param   参数
    * @return
    */
    public OwnResult<OwnPageResult<QueryPatientRecordDto>> getQueryPatientRecord(QueryPatientRecordParam param) throws Exception {
        IPage<QueryPatientRecordDto> pageResult = busQueryPatientMapper.getQueryPatientRecord(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(pageResult.getTotal(), pageResult.getSize(), pageResult.getCurrent(), pageResult.getRecords()));
    }

}