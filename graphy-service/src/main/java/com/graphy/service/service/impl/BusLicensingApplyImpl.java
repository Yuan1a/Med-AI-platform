package com.graphy.service.service.impl;
import com.graphy.db.entity.*;
import com.graphy.service.bean.dto.ApprovalNumberDto;

import com.graphy.service.bean.param.SaveApprovalParam;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.db.service.TbBusUnitService;
import com.graphy.db.service.TbPlatformUserService;
import com.graphy.service.bean.OwnUserInfo;
import com.graphy.service.bean.param.SavaLicensingApplyParam;
import com.graphy.service.bean.dto.LicensingImagesDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.LicensingImagesParam;
import com.graphy.service.service.BusPatientService;
import com.graphy.service.service.BusPortraitReportService;
import com.graphy.service.service.PlatformCommonService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.BusLicensingApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbBusLicensingApplyMapper;
import com.graphy.service.service.BusLicensingApplyService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;
import java.util.*;

/**
 * @auther qwt
 * @create 2022-12-08 08:38:40
 * @describe 影像报告授权申请管理
 */
@Service("com.graphy.service.service.impl.buslicensingapplyimpl")
@RequiredArgsConstructor
@Slf4j
public class BusLicensingApplyImpl extends ServiceImpl<TbBusLicensingApplyMapper, TbBusLicensingApplyEntity> implements BusLicensingApplyService {

    private final BusLicensingApplyMapper busLicensingApplyMapper;

    private final BusPortraitReportService busPortraitReportService;

    private final PlatformCommonService platformCommonService;

    private final TbPlatformUserService tbPlatformUserService;

    private final TbBusUnitService tbBusUnitService;
    private final BusPatientService busPatientService;
    /**
     * auther： qwt
     * create： 2022-12-08 08:42:48
     * describe： 获取影像报告申请
     * @param param   参数
     * @return
     */
    public OwnResult<OwnPageResult<LicensingImagesDto>> getLicensingApply(LicensingImagesParam param) throws Exception {
        IPage<LicensingImagesDto> pageResult = busLicensingApplyMapper.getLicensingApply(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(pageResult.getTotal(), pageResult.getSize(), pageResult.getCurrent(), pageResult.getRecords()));
    }

    /**
     * auther： qwt
     * create： 2022-12-08 09:31:12
     * describe： 保存影像授权申请
     * @param param   参数
     * @return
     */
    public OwnResult<Boolean> saveApply(SavaLicensingApplyParam param) throws Exception {
        if (StrUtil.hasEmpty(param.getPatientId())) {
            return OwnResult.error("请设置患者ID");
        }
        // if (StrUtil.hasEmpty(param.getApprovalUnit())) {
        // return OwnResult.error("请设置授权机构");
        // }
//         busPatientService.list(new LambdaQueryWrapper<TbBusPatientEntity>()
//         .eq())
        List<TbBusLicensingApplyEntity> tbBusLicensingApplyEntities = this.list(new LambdaQueryWrapper<TbBusLicensingApplyEntity>()
                .eq(TbBusLicensingApplyEntity::getPatientId, param.getPatientId())
                .eq(TbBusLicensingApplyEntity::getStatus, "1"));

        OwnUserInfo userInfo = platformCommonService.getUserInfo();
        TbPlatformUserEntity userEntity = tbPlatformUserService.getById(userInfo.getUserId());
        TbBusUnitEntity unitEntity = tbBusUnitService.getById(userEntity.getUnit());

        for (TbBusLicensingApplyEntity applyEntity:tbBusLicensingApplyEntities) {
            if (applyEntity.getApplyUnit().equals(unitEntity.getId())){
                return OwnResult.error("该患者的信息已申请过授权");
            }
        }

        param.setApprovalUser(userEntity.getName());
        param.setApplyUnit(unitEntity.getId());
        TbBusPatientEntity busPatientServiceById = busPatientService.getById(param.getPatientId());
        param.setApprovalUnit(busPatientServiceById.getDiagnosisUnit());
        param.setApprovalStatus("0");
        TbBusLicensingApplyEntity applyEntity = JSONObject.parseObject(JSONObject.toJSONString(param), TbBusLicensingApplyEntity.class);
        if (StrUtil.hasEmpty(applyEntity.getId())) {
            this.save(applyEntity);
        } else {
            this.updateById(applyEntity);
        }
        return OwnResult.result(true);
    }

    /**
     * auther： qwt
     * create： 2022-12-08 10:04:19
     * describe： 删除影像授权申请
     * @param id   主键ID
     * @return
     */
    public OwnResult<Boolean> delApply(String id) throws Exception {
        if (StrUtil.hasEmpty(id))
            return OwnResult.error("请设置主键ID");
        TbBusLicensingApplyEntity entity = new TbBusLicensingApplyEntity();
        entity.setId(id);
        entity.setStatus("0");
        this.updateById(entity);
        return OwnResult.result(true);
    }

    /**
     * auther： qwt
     * create： 2022-12-08 14:37:38
     * describe： 保存影像授权审批
     * @param param   参数
     * @return
     */
    public OwnResult<Boolean> saveApproval(SaveApprovalParam param) throws Exception {
        if (StrUtil.hasEmpty(param.getId())) {
            return OwnResult.error("请设置ID");
        }
        if (StrUtil.hasEmpty(param.getApprovalStatus()) || "0".equals(param.getApprovalStatus())) {
            return OwnResult.error("请设置审批状态");
        }
        TbBusLicensingApplyEntity applyEntity = this.getById(param.getId());
        applyEntity.setApprovalStatus(param.getApprovalStatus());
        applyEntity.setApprovalTime(new Date());
        this.updateById(applyEntity);
        return OwnResult.result(true);
    }

    /**
    * auther： qwt
    * create： 2022-12-12 11:16:05
    * describe： 获取该机构的需要审批的授权申请数
    * @param approvalUnit   审批机构
    * @return
    */
    public OwnResult<ApprovalNumberDto> getApprovalNumber(String approvalUnit) throws Exception {
       ApprovalNumberDto approvalNumber = busLicensingApplyMapper.getApprovalNumber(approvalUnit);
        return OwnResult.result(approvalNumber);
    }

}