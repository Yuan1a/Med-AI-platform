package com.graphy.service.service.impl;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.blockchain.BlockChainApi;
import com.graphy.config.BaseConfig;
import com.graphy.db.entity.TbBusPatientEntity;
import com.graphy.db.entity.TbBusUnitEntity;
import com.graphy.db.entity.TbPlatformUserEntity;
import com.graphy.db.service.TbBusUnitService;
import com.graphy.db.service.TbPlatformUserService;
import com.graphy.service.bean.OwnUserInfo;
import com.graphy.service.bean.dto.OnChainDataDto;
import com.graphy.service.bean.param.SaveDiagnoseRecordParam;
import com.graphy.service.bean.dto.DiagnoseRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.DiagnoseRecordParam;
import com.graphy.service.service.BusPatientService;
import com.graphy.service.service.BusPortraitReportService;
import com.graphy.service.service.PlatformCommonService;
import com.graphy.unit.OwnCommon;
import com.graphy.unit.crypt.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.BusDiagnoseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbBusDiagnoseMapper;
import com.graphy.db.entity.TbBusDiagnoseEntity;
import com.graphy.service.service.BusDiagnoseService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;

/**
 * @auther lsd
 * @create 2022-03-01 11:23:17
 * @describe 诊断信息
 */
@Service("com.graphy.service.service.impl.busdiagnoseimpl")
@RequiredArgsConstructor
@Slf4j
public class BusDiagnoseImpl extends ServiceImpl<TbBusDiagnoseMapper, TbBusDiagnoseEntity> implements BusDiagnoseService {

    private final BusDiagnoseMapper busDiagnoseMapper;
    @Autowired
    private final BusPatientService busPatientService;
    private final PlatformCommonService platformCommonService;
    private final TbPlatformUserService tbPlatformUserService;
    private final TbBusUnitService tbBusUnitService;
    private final BusPortraitReportService busPortraitReportService;
    @Value("${storage-host}")
    private String storageHost;
    /**
     * auther： lsd
     * create： 2022-03-01 12:52:43
     * describe： 获取记录
     *
     * @param param 参数
     * @return
     */
    public OwnResult<OwnPageResult<DiagnoseRecordDto>> getDiagnoseRecord(DiagnoseRecordParam param) throws Exception {
        IPage<DiagnoseRecordDto> pageResult = busDiagnoseMapper.getDiagnoseRecord(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(pageResult.getTotal(), pageResult.getSize(), pageResult.getCurrent(), pageResult.getRecords()));
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:53:36
     * describe： 保存信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<Boolean> saveDiagnoseRecord(SaveDiagnoseRecordParam param) throws Exception {
        if (StrUtil.hasEmpty(param.getPatientId()))
            return OwnResult.error("请设置患者ID");
        if (StrUtil.hasEmpty(param.getDiagnosisDescribe()))
            return OwnResult.error("请设置诊断描述");
        if (StrUtil.hasEmpty(param.getIllnessId()))
            return OwnResult.error("请设置诊断病种");
        if (StrUtil.hasEmpty(param.getDiagnosisDoctor()))
            return OwnResult.error("请设置诊断医生");
        if (StrUtil.hasEmpty(param.getDiagnosisUnit()))
            return OwnResult.error("请设置诊断机构");

//        OwnUserInfo userInfo = platformCommonService.getUserInfo();
//        TbPlatformUserEntity userEntity = tbPlatformUserService.getById(userInfo.getUserId());
//        TbBusUnitEntity unitEntity = tbBusUnitService.getById(userEntity.getUnit());
//        param.setDiagnosisDoctor(userEntity.getName());
//        param.setDiagnosisUnit(unitEntity.getId());
//        param.setDiagnosisStatus("1");
//        TbBusDiagnoseEntity tbBusDiagnoseEntity = this.getOne(new LambdaQueryWrapper<TbBusDiagnoseEntity>().eq(TbBusDiagnoseEntity::getStatus, "1")
//                .eq(TbBusDiagnoseEntity::getPatientId, param.getPatientId()));
//        if (OwnCommon.isNullOrEmpty(tbBusDiagnoseEntity)){
//
//        }
//        TbBusPatientEntity patientServiceById = busPatientService.getById(param.getPatientId());
//        OnChainDataDto onChainDataDto = JSONObject.parseObject(busPortraitReportService.blockchainImageExtraction(patientServiceById.getId()), OnChainDataDto.class);
//
//        TbBusDiagnoseEntity orm = JSONObject.parseObject(JSONObject.toJSONString(param), TbBusDiagnoseEntity.class);
//        TbBusPatientEntity patientInfo = busPatientService.getById(param.getPatientId());
//        patientInfo.setDiagnosisStatus("1");
//        busPatientService.updateById(patientInfo);
//        if (StrUtil.hasEmpty(orm.getId())) {
//            this.save(orm);
//        } else {
//            this.updateById(orm);
//        }
//        onChainDataDto.setTbBusDiagnoseEntity(orm);
//        String FileHash = Api.encrypt(JSONObject.toJSONString(onChainDataDto), BaseConfig.ENCRYPTION_KEY);
//        String Txid = BlockChainApi.blockChainStorage(storageHost, FileHash, patientServiceById.getId(), patientServiceById.getRadiationId());
//
//
//        patientServiceById.setTxid(Txid);
//        busPatientService.updateById(patientServiceById);
        return OwnResult.result(true);
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:53:56
     * describe： 删除诊断信息
     *
     * @param id 主键
     * @return
     */
    public OwnResult<Boolean> delDiagnoseRecord(String id) throws Exception {
        if (StrUtil.hasEmpty(id)) return OwnResult.error("请设置主键");
        TbBusDiagnoseEntity tbBusDiagnoseEntity = this.getById(id);
        TbBusPatientEntity patientService = busPatientService.getById(tbBusDiagnoseEntity.getPatientId());
        patientService.setDiagnosisStatus("0");
        busPatientService.updateById(patientService);
        TbBusDiagnoseEntity delOrm = new TbBusDiagnoseEntity();
        delOrm.setId(id);
        delOrm.setStatus("0");
        this.updateById(delOrm);
        return OwnResult.result(true);
    }

}