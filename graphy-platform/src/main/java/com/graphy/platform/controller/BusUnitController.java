package com.graphy.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.graphy.db.service.TbPlatformCodeTypeService;
import com.graphy.service.bean.param.SaveUnitRecordParam;
import com.graphy.service.bean.dto.UnitRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.UnitRecordParam;
import cn.hutool.core.util.StrUtil;
import com.graphy.db.entity.TbBusPortraitReportEntity;
import com.graphy.db.entity.TbBusUnitEntity;
import com.graphy.service.service.PlatformCommonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.BusUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 11:21:01
 * @describe 机构管理
 */
@Data
@Controller("com.graphy.platform.controller.busunitcontroller")
@RequiredArgsConstructor
@Api(tags = "机构管理")
@RequestMapping("/pf/BusUnit")
@Slf4j
public class BusUnitController {

    private final BusUnitService busUnitService;
    private final TbPlatformCodeTypeService tbPlatformCodeTypeService;
    private final PlatformCommonService platformCommonService;

    private void addCodes(Model model) throws Exception {
        model.addAttribute("unitType", JSONObject.toJSONString(platformCommonService.getCodes("unit-type")));
        model.addAttribute("unitGrade", JSONObject.toJSONString(platformCommonService.getCodes("unit-grade")));
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
        return "/bus/unit/index";
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
    @ApiImplicitParams({@ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String")})
    @ApiOperation(value = "编辑页面", notes = "编辑页面")
    public String edit(@RequestParam(value = "id", required = false) String id, Model model) throws Exception {
        TbBusUnitEntity orm = new TbBusUnitEntity();
        if (!StrUtil.hasEmpty(id)) {
            orm = busUnitService.getById(id);
        } else {
        }
        model.addAttribute("orm", orm);
        addCodes(model);
        return "/bus/unit/edit";
    }

    /**
     * auther： lsd
     * create： 2022-03-01 11:48:53
     * describe： 获取机构信息列表
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getUnitRecord")
    @ApiOperation(value = "获取机构信息列表", notes = "获取机构信息列表")
    @ResponseBody
    private OwnResult<OwnPageResult<UnitRecordDto>> getUnitRecord(@RequestBody UnitRecordParam param) throws Exception {
        return busUnitService.getUnitRecord(param);
    }

    /**
     * auther： lsd
     * create： 2022-03-01 11:50:52
     * describe： 保存机构信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveUnitRecord")
    @ApiOperation(value = "保存机构信息", notes = "保存机构信息")
    @ResponseBody
    private OwnResult<Boolean> saveUnitRecord(@RequestBody SaveUnitRecordParam param) throws Exception {
        return busUnitService.saveUnitRecord(param);
    }

    /**
     * auther： lsd
     * create： 2022-03-01 11:51:16
     * describe： 删除机构信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delUnitRecord")
    @ApiOperation(value = "删除机构信息", notes = "删除机构信息")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "String")
    })
    private OwnResult<Boolean> delUnitRecord(String id) throws Exception {
        return busUnitService.delUnitRecord(id);
    }
}