package com.graphy.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.graphy.db.entity.*;
import com.graphy.db.service.*;
import com.graphy.service.bean.OwnUserInfo;
import com.graphy.service.bean.param.SaveDiagnoseRecordParam;
import com.graphy.service.bean.dto.DiagnoseRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.DiagnoseRecordParam;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.service.service.BusPortraitReportService;
import com.graphy.service.service.PlatformCommonService;
import com.graphy.unit.OwnValueText;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.BusDiagnoseService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 11:23:17
 * @describe 诊断信息
 */
@Data
@Controller("com.graphy.platform.controller.busdiagnosecontroller")
@RequiredArgsConstructor
@Api(tags = "诊断信息")
@RequestMapping("/pf/BusDiagnose")
@Slf4j

public class BusDiagnoseController {

    private final BusDiagnoseService busDiagnoseService;
    private final TbBusPatientService tbBusPatientService;
    private final TbPlatformCodeTypeService tbPlatformCodeTypeService;
    private final PlatformCommonService platformCommonService;
    private final TbPlatformUserService tbPlatformUserService;
    private final TbBusUnitService tbBusUnitService;
    private final TbPlatformCodeService tbPlatformCodeService;
    private final BusPortraitReportService busPortraitReportService;
    private final TbBusIllnessService tbBusIllnessService;
    private void addCodes(Model model) throws Exception {
        model.addAttribute("office", JSONObject.toJSONString(platformCommonService.getCodes("office")));
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "首页", notes = "首页")
    private String index(Model model) throws Exception {
        addCodes(model);
        String unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>()
                .eq(TbBusUnitEntity::getStatus, "1")
        ), "id", "name"));
        model.addAttribute("unit", unit);
        return "/bus/diagnose/index";
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
        TbBusDiagnoseEntity orm = new TbBusDiagnoseEntity();
        String patientName = "";
        String unitCode ="";
        String radiatioId = "";
        TbBusPortraitReportEntity report = new TbBusPortraitReportEntity();
        OwnUserInfo userInfo = platformCommonService.getUserInfo();
        TbPlatformUserEntity userEntity = tbPlatformUserService.getById(userInfo.getUserId());
        TbBusUnitEntity unitEntity = tbBusUnitService.getById(userEntity.getUnit());
        if (!StrUtil.hasEmpty(id)) {
            orm = busDiagnoseService.getById(id);
            TbBusPatientEntity patientServiceById = tbBusPatientService.getById(orm.getPatientId());
            radiatioId = patientServiceById.getRadiationId();
        } else {
            orm.setDiagnosisUnit(unitEntity.getId());
        }
        String unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>()
                .eq(TbBusUnitEntity::getStatus, "1")
        ), "id", "name"));
        String illnessId = JSONObject.toJSONString(OwnValueText.list(tbBusIllnessService.list(new LambdaQueryWrapper<TbBusIllnessEntity>()
                .eq(TbBusIllnessEntity :: getStatus, "1").eq(TbBusIllnessEntity :: getIsUse,"1")
        ), "id", "name"));
        model.addAttribute("illnessId", illnessId);
        model.addAttribute("unit", unit);
        model.addAttribute("radiationId", radiatioId);
        model.addAttribute("orm", orm);
        addCodes(model);
        return "/bus/diagnose/edit";
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:52:43
     * describe： 获取记录
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getDiagnoseRecord")
    @ApiOperation(value = "获取记录", notes = "获取记录")
    @ResponseBody
    private OwnResult<OwnPageResult<DiagnoseRecordDto>> getDiagnoseRecord(@RequestBody DiagnoseRecordParam param) throws Exception {
        return busDiagnoseService.getDiagnoseRecord(param);
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:53:36
     * describe： 保存信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveDiagnoseRecord")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ResponseBody
    private OwnResult<Boolean> saveDiagnoseRecord(@RequestBody SaveDiagnoseRecordParam param) throws Exception {
        return busDiagnoseService.saveDiagnoseRecord(param);
    }

    /**
    * auther： lsd
    * create： 2022-03-01 12:53:56
    * describe： 删除诊断信息
    */
    @RequestMapping(method = RequestMethod.POST, value = "/delDiagnoseRecord")
    @ApiOperation(value="删除诊断信息", notes="删除诊断信息")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "String")
    })
    private OwnResult<Boolean> delDiagnoseRecord(String id) throws Exception {
        return busDiagnoseService.delDiagnoseRecord(id);
    }
}