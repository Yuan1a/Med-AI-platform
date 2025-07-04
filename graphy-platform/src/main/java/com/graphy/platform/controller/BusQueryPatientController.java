package com.graphy.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.db.entity.TbBusUnitEntity;
import com.graphy.db.entity.TbPlatformUserEntity;
import com.graphy.db.service.TbBusUnitService;
import com.graphy.db.service.TbPlatformUserService;
import com.graphy.service.bean.OwnUserInfo;
import com.graphy.service.bean.dto.PatientRecordDto;
import com.graphy.service.bean.dto.QueryPatientRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PatientRecordParam;
import com.graphy.service.bean.param.QueryPatientRecordParam;

import com.graphy.service.service.BusPatientService;
import com.graphy.service.service.PlatformCommonService;
import com.graphy.unit.OwnValueText;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.BusQueryPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 15:55:16
 * @describe 患者选择
 */
@Data
@Controller("com.graphy.platform.controller.busquerypatientcontroller")
@RequiredArgsConstructor
@Api(tags = "患者选择")
@RequestMapping("/pf/BusQueryPatient")
@Slf4j
public class BusQueryPatientController {

    private final BusQueryPatientService busQueryPatientService;
    private final BusPatientService busPatientService;
    private final TbBusUnitService tbBusUnitService;
    private final PlatformCommonService platformCommonService;
    private final TbPlatformUserService tbPlatformUserService;
    /**
     * 搜索页面
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "搜索页面", notes = "搜索页面")
    private String index(String choosetype,Model model) throws Exception {
        // type为1时，选择患者界面有患者详情，为2时没有。
        model.addAttribute("choosetype",choosetype);
        return "/bus/querypatient/index";
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
        if (!StrUtil.hasEmpty(id)) {
            PatientRecordParam patientRecordParam = new PatientRecordParam();
            patientRecordParam.setId(id);
            orm = busPatientService.getPatientRecord(patientRecordParam).result.getRecords().get(0);
        } else {
        }
        String unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>()
                .eq(TbBusUnitEntity::getStatus, "1")
        ), "id", "name"));
        model.addAttribute("unit", unit);
        model.addAttribute("orm", orm);
        return "/bus/querypatient/details";
    }
    /**
     * auther： lsd
     * create： 2022-03-01 15:59:46
     * describe： 搜索患者记录
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getQueryPatientRecord")
    @ApiOperation(value = "搜索患者记录", notes = "搜索患者记录")
    @ResponseBody
    private OwnResult<OwnPageResult<QueryPatientRecordDto>> getQueryPatientRecord(@RequestBody Map<String,String> map ,@RequestBody QueryPatientRecordParam param) throws Exception {

        String choosetype = map.get("choosetype");
        OwnUserInfo userInfo = platformCommonService.getUserInfo();
        TbPlatformUserEntity userEntity = tbPlatformUserService.getById(userInfo.getUserId());
        TbBusUnitEntity unitEntity = tbBusUnitService.getById(userEntity.getUnit());
        param.setUnit(unitEntity.getId());
        param.setChoosetype(choosetype);
        return busQueryPatientService.getQueryPatientRecord(param);
    }
}