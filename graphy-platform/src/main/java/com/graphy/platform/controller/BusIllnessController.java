package com.graphy.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.graphy.db.service.TbPlatformCodeTypeService;
import com.graphy.service.bean.param.SaveIllnessRecordParam;
import com.graphy.service.bean.dto.IllnessRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.IllnessRecordParam;
import cn.hutool.core.util.StrUtil;
import com.graphy.db.entity.TbBusDiagnoseEntity;
import com.graphy.db.entity.TbBusIllnessEntity;
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
import com.graphy.service.service.BusIllnessService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 11:21:35
 * @describe 病种管理
 */
@Data
@Controller("com.graphy.platform.controller.busillnesscontroller")
@RequiredArgsConstructor
@Api(tags = "病种管理")
@RequestMapping("/pf/BusIllness")
@Slf4j
public class BusIllnessController {

    private final BusIllnessService busIllnessService;
    private final TbPlatformCodeTypeService tbPlatformCodeTypeService;
    private final PlatformCommonService platformCommonService;

    private void addCodes(Model model) throws Exception {
        model.addAttribute("illnessType", JSONObject.toJSONString(platformCommonService.getCodes("illness-type")));
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
        return "/bus/Illness/index";
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
        TbBusIllnessEntity orm = new TbBusIllnessEntity();
        if (!StrUtil.hasEmpty(id)) {
            orm = busIllnessService.getById(id);
        } else {
            orm.setIsUse("1");
        }
        model.addAttribute("orm", orm);
        addCodes(model);
        return "/bus/Illness/edit";
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:42:07
     * describe： 获取病种信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getIllnessRecord")
    @ApiOperation(value = "获取病种信息", notes = "获取病种信息")
    @ResponseBody
    private OwnResult<OwnPageResult<IllnessRecordDto>> getIllnessRecord(@RequestBody IllnessRecordParam param) throws Exception {
        return busIllnessService.getIllnessRecord(param);
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:43:00
     * describe： 保存病种信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveIllnessRecord")
    @ApiOperation(value = "保存病种信息", notes = "保存病种信息")
    @ResponseBody
    private OwnResult<Boolean> saveIllnessRecord(@RequestBody SaveIllnessRecordParam param) throws Exception {
        return busIllnessService.saveIllnessRecord(param);
    }

    /**
    * auther： lsd
    * create： 2022-03-01 12:43:23
    * describe： 删除病种信息
    */
    @RequestMapping(method = RequestMethod.POST, value = "/delIllnessRecord")
    @ApiOperation(value="删除病种信息", notes="删除病种信息")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "String")
    })
    private OwnResult<Boolean> delIllnessRecord(String id) throws Exception {
        return busIllnessService.delIllnessRecord(id);
    }
}