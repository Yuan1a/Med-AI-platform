package com.graphy.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.blockchain.BlockChainApi;
import com.graphy.config.BaseConfig;
import com.graphy.db.entity.TbBusPortraitReportImagesEntity;
import com.graphy.db.entity.TbBusUnitEntity;
import com.graphy.db.entity.TbPlatformUserEntity;
import com.graphy.db.service.TbBusPortraitReportImagesService;
import com.graphy.db.service.TbBusUnitService;
import com.graphy.db.service.TbPlatformUserService;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnUserInfo;
import com.graphy.service.bean.param.PortraitReportRecordParam;
import com.graphy.service.bean.param.SavePortraitReportRecordParam;
import com.graphy.service.bean.dto.PortraitReportRecordDto;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.service.PlatformCommonService;
import com.graphy.unit.crypt.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.BusPortraitReportMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbBusPortraitReportMapper;
import com.graphy.db.entity.TbBusPortraitReportEntity;
import com.graphy.service.service.BusPortraitReportService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;

/**
 * @auther lsd
 * @create 2022-03-01 11:22:46
 * @describe 影像报告
 */
@Service("com.graphy.service.service.impl.busportraitreportimpl")
@RequiredArgsConstructor
@Slf4j
public class BusPortraitReportImpl extends ServiceImpl<TbBusPortraitReportMapper, TbBusPortraitReportEntity> implements BusPortraitReportService {

    private final BusPortraitReportMapper busPortraitReportMapper;
    private final PlatformCommonService platformCommonService;
    private final TbPlatformUserService tbPlatformUserService;
    private final TbBusUnitService tbBusUnitService;
    private final TbBusPortraitReportImagesService tbBusPortraitReportImagesService;
    @Value("${storage-host}")
    private String storageHost;
    @Value("${extract-host}")
    private String extractHost;

    /**
     * auther： lsd
     * create： 2022-03-01 12:48:05
     * describe： 获取记录
     *

     * @return
     */
    public OwnResult<OwnPageResult<PortraitReportRecordDto>> getPortraitReportRecord(PortraitReportRecordParam param) throws Exception {
        IPage<PortraitReportRecordDto> pageResult = busPortraitReportMapper.getPortraitReportRecord(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(pageResult.getTotal(), pageResult.getSize(), pageResult.getCurrent(), pageResult.getRecords()));
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:48:37
     * describe： 保存信息(启用)
     *
     * @param param 参数
     * @return
     */
    public OwnResult<Boolean> savePortraitReportRecord(SavePortraitReportRecordParam param) throws Exception {
        if (StrUtil.hasEmpty(param.getPatientId()))
            return OwnResult.error("请设置患者ID");
        if (StrUtil.hasEmpty(param.getStraightMatter())){
            return OwnResult.error("请设置影像报告");
        }
//        OwnUserInfo userInfo = platformCommonService.getUserInfo();
//        TbPlatformUserEntity userEntity = tbPlatformUserService.getById(userInfo.getUserId());
//        TbBusUnitEntity unitEntity = tbBusUnitService.getById(userEntity.getUnit());
//        param.s(userEntity.getName());
//        param.setDiagnosisUnit(unitEntity.getId());
//        param.setDiagnosisTime(new Date());

//        TbBusPortraitReportEntity orm = JSONObject.parseObject(JSONObject.toJSONString(param), TbBusPortraitReportEntity.class);
//        if (StrUtil.hasEmpty(orm.getId())) {
//            param.setId(UUID.randomUUID().toString());
//            this.save(orm);
//        } else {
//            this.updateById(orm);
//        }
//        TbBusPortraitReportImagesEntity imagesEntity1 = new TbBusPortraitReportImagesEntity();
//        imagesEntity1.setStatus("0");
//        tbBusPortraitReportImagesService.update(imagesEntity1,new LambdaQueryWrapper<TbBusPortraitReportImagesEntity>()
//        .eq(TbBusPortraitReportImagesEntity :: getPatientId,orm.getId())
//        .eq(TbBusPortraitReportImagesEntity :: getStatus,"1"));
//
//        for ( String file:param.getFiles()){
//            if (file.startsWith("data")) {
//                int i=1;
//                String head = file.substring(0, file.indexOf(",") + 1);
//                String type = head.substring(head.indexOf("/") + 1, head.indexOf(";"));
//                String base64 = file.substring(file.indexOf(",") + 1);
//                String FileHash = Api.encrypt(base64, BaseConfig.ENCRYPTION_KEY);
//                TbBusPortraitReportImagesEntity imagesEntity = new TbBusPortraitReportImagesEntity();
//                imagesEntity.setId(UUID.randomUUID().toString());
//                String Txid = BlockChainApi.blockChainStorage(storageHost, FileHash, imagesEntity.getId(), orm.getId());
//                i++;
//                imagesEntity.setImageFormat(type);
//                tbBusPortraitReportImagesService.save(imagesEntity);
//            }
//        }
        return OwnResult.result(true);
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:48:55
     * describe： 删除信息
     *
     * @param id 主键
     * @return
     */
    public OwnResult<Boolean> delPortraitReportRecord(String id) throws Exception {
        if (StrUtil.hasEmpty(id)) return OwnResult.error("请设置主键");

        TbBusPortraitReportEntity delOrm = new TbBusPortraitReportEntity();
        delOrm.setId(id);
        delOrm.setStatus("0");
        this.updateById(delOrm);

        return OwnResult.result(true);
    }
    /**
     * auther： qwt
     * create： 2022-12-05 12:48:55
     * describe： 区块链提取信息
     *
     * @param id 主键
     * @return
     */
    public String blockchainImageExtraction(String id) throws Exception {
        String FileHash = BlockChainApi.blockChainExtract(extractHost,id);
        String patientInfo = Api.decrypt(FileHash, BaseConfig.ENCRYPTION_KEY);
        return patientInfo;
    }
}