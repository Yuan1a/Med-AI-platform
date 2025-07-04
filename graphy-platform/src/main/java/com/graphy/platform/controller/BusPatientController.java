package com.graphy.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.db.entity.*;
import com.graphy.db.service.*;
import com.graphy.service.bean.OwnUserInfo;
import com.graphy.service.bean.dto.OnChainDataDto;
import com.graphy.service.bean.param.SavePatientRecordParam;
import com.graphy.service.bean.dto.PatientRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PatientRecordParam;
import cn.hutool.core.util.StrUtil;
import com.graphy.service.service.BusPortraitReportImagesService;
import com.graphy.service.service.BusPortraitReportService;
import com.graphy.service.service.PlatformCommonService;
import com.graphy.unit.OwnValueText;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.BusPatientService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther lsd
 * @create 2022-03-01 11:22:06
 * @describe 患者管理
 */
@Data
@Controller("com.graphy.platform.controller.buspatientcontroller")
@RequiredArgsConstructor
@Api(tags = "患者管理")
@RequestMapping("/pf/BusPatient")
@Slf4j
public class BusPatientController {

    private final BusPatientService busPatientService;
    private final TbBusUnitService tbBusUnitService;
    private final TbBusIllnessService tbBusIllnessService;
    private final TbBusDiagnoseService tbBusDiagnoseService;
    private final BusPortraitReportService busPortraitReportService;
    private final BusPortraitReportImagesService busPortraitReportImagesService;
//    private final BusPortraitReportController busPortraitReportController;
    public  final PlatformCommonService platformCommonService;
    public  final TbPlatformUserService tbPlatformUserService;
    private final TbBusPatientService tbBusPatientService;
    @Value("${server.servlet.context-path}")
    private String webLevel;
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "首页", notes = "首页")
    private String index(Model model) throws Exception {
        OwnUserInfo userInfo = platformCommonService.getUserInfo();
        TbPlatformUserEntity userEntity = tbPlatformUserService.getById(userInfo.getUserId());
        String unit=null;
        if (!"admin".equals(userInfo.getUserName())){
            unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>()
                    .eq(TbBusUnitEntity::getStatus, "1").eq(TbBusUnitEntity::getId,userEntity.getUnit())
            ), "id", "name"));
        }else {
            unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>()
                    .eq(TbBusUnitEntity::getStatus, "1")
            ), "id", "name"));
        }

        String illnessId = JSONObject.toJSONString(OwnValueText.list(tbBusIllnessService.list(new LambdaQueryWrapper<TbBusIllnessEntity>()
                .eq(TbBusIllnessEntity :: getStatus, "1").eq(TbBusIllnessEntity :: getIsUse,"1")
        ), "id", "name"));
        model.addAttribute("illnessId", illnessId);
        model.addAttribute("unit", unit);
        return "/bus/patient/index";
    }

    /**
     * 编辑页面
     *
     * @param id    主键
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "编辑页面", notes = "编辑页面")
    public String edit(@RequestParam(value = "id", required = false) String id, Model model) throws Exception {
        PatientRecordDto orm = new PatientRecordDto();
        TbBusDiagnoseEntity tbBusDiagnoseEntity = new TbBusDiagnoseEntity();
        if (!StrUtil.hasEmpty(id)) {
            PatientRecordParam patientRecordParam = new PatientRecordParam();
            patientRecordParam.setId(id);
            orm = busPatientService.getPatientRecord(patientRecordParam).result.getRecords().get(0);
            if ("1".equals(orm.getDiagnosisStatus())){
//                tbBusDiagnoseService.get
            }
        } else {
        }
        String unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>()
                .eq(TbBusUnitEntity::getStatus, "1")
        ), "id", "name"));
        String illnessId = JSONObject.toJSONString(OwnValueText.list(tbBusIllnessService.list(new LambdaQueryWrapper<TbBusIllnessEntity>()
                .eq(TbBusIllnessEntity :: getStatus, "1").eq(TbBusIllnessEntity :: getIsUse,"1")
        ), "id", "name"));
        model.addAttribute("illnessId", illnessId);
        model.addAttribute("unit", unit);
        model.addAttribute("orm", orm);
        return "/bus/patient/edit";
    }
    /**
     * 患者详情
     *
     * @param id    主键
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "患者详情页面", notes = "患者详情页面")
    public String details(@RequestParam(value = "id", required = false) String id, Model model) throws Exception {
        PatientRecordDto orm = new PatientRecordDto();
        List<TbBusPortraitReportImagesEntity> imagesServiceList = new ArrayList<>();
        if (!StrUtil.hasEmpty(id)) {
            PatientRecordParam patientRecordParam = new PatientRecordParam();
            patientRecordParam.setId(id);
            orm = busPatientService.getPatientRecord(patientRecordParam).result.getRecords().get(0);
            TbBusPatientEntity tbBusPatientServiceById = tbBusPatientService.getById(id);
            OnChainDataDto onChainDataDto = JSONObject.parseObject(busPortraitReportService.blockchainImageExtraction(tbBusPatientServiceById.getId()), OnChainDataDto.class);
            List<String> imagesIdList = onChainDataDto.getImagesIdList();
            for (String imagesId:imagesIdList ){
                TbBusPortraitReportImagesEntity imagesServiceById = busPortraitReportImagesService.getById(imagesId);
                imagesServiceList.add(imagesServiceById);
            }
        } else {
        }
        String unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>()
                .eq(TbBusUnitEntity::getStatus, "1")
        ), "id", "name"));
        model.addAttribute("unit", unit);
        model.addAttribute("orm", orm);
        model.addAttribute("orm", orm);
        model.addAttribute("imagesServiceList", imagesServiceList);
        return "/bus/patient/details";
    }
    /**
     * auther： lsd
     * create： 2022-03-01 11:54:13
     * describe： 获取患者信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getPatientRecord")
    @ApiOperation(value = "获取患者信息", notes = "获取患者信息")
    @ResponseBody
    private OwnResult<OwnPageResult<PatientRecordDto>> getPatientRecord(@RequestBody PatientRecordParam param) throws Exception {
        OwnUserInfo userInfo = platformCommonService.getUserInfo();
        if (!"admin".equals(userInfo.getUserName())){
            TbPlatformUserEntity userEntity = tbPlatformUserService.getById(userInfo.getUserId());
            TbBusUnitEntity unitEntity = tbBusUnitService.getById(userEntity.getUnit());
            param.setDiagnosisUnit(unitEntity.getId());
        }
        return busPatientService.getPatientRecord(param);
    }

    /**
     * auther： lsd
     * create： 2022-03-01 11:54:46
     * describe： 保存患者信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/savePatientRecord")
    @ApiOperation(value = "保存患者信息", notes = "保存患者信息")
    @ResponseBody
    private OwnResult<Boolean> savePatientRecord(@RequestBody SavePatientRecordParam param) throws Exception {
        return busPatientService.savePatientRecord(param);
    }

    /**
    * auther： lsd
    * create： 2022-03-01 11:55:09
    * describe： 删除患者信息
    */
    @RequestMapping(method = RequestMethod.POST, value = "/delPatientRecord")
    @ApiOperation(value="删除患者信息", notes="删除患者信息")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "String")
    })
    private OwnResult<Boolean> delPatientRecord(String id) throws Exception {
        return busPatientService.delPatientRecord(id);
    }
}