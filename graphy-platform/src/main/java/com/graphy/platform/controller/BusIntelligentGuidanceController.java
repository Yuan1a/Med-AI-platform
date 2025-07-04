package com.graphy.platform.controller;
import com.graphy.service.bean.param.IntelligentImageAnalysiParam;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.db.entity.TbBusUnitEntity;
import com.graphy.db.service.TbBusUnitService;
import com.graphy.service.bean.dto.IllnessStatisticsDto;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.IllnessStatisticsParam;
import com.graphy.unit.OwnValueText;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.ui.Model;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.BusIntelligentGuidanceService;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

/**
 * @auther qwt
 * @create 2023-06-15 09:06:08
 * @describe 智能导诊分析
 */
@Data
@Controller("com.graphy.platform.controller.busintelligentguidancecontroller")
@RequiredArgsConstructor
@Api(tags = "智能导诊分析")
@RequestMapping("/pf/BusIntelligentGuidance")
@Slf4j
public class BusIntelligentGuidanceController {

    private final BusIntelligentGuidanceService busIntelligentGuidanceService;

    private final TbBusUnitService tbBusUnitService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "列表页面", notes = "列表页面")
    private String index(Model model) throws Exception {
        return "/bus/busintelligentguidance/index";
    }

    /**
     * 病种统计页面
     *
     * @return
     */
    @RequestMapping(value = "/diseaseDisplay", method = RequestMethod.GET)
    @ApiOperation(value = "病种统计页面", notes = "病种统计页面")
    private String diseaseDisplay(Model model) throws Exception {
        return "/bus/busintelligentguidance/diseaseDisplay";
    }

    /**
     * 患者列表页面
     *
     * @return
     */
    @RequestMapping(value = "/patientsList", method = RequestMethod.GET)
    @ApiOperation(value = "患者列表页面", notes = "患者列表页面")
    private String patientsList(@RequestParam(value = "illnessId", required = false) String illnessId, Model model) throws Exception {
        String unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>().eq(TbBusUnitEntity::getStatus, "1")), "id", "name"));
        model.addAttribute("unit", unit);
        model.addAttribute("illnessId", illnessId);
        return "/bus/busintelligentguidance/patientsList";
    }

    /**
     * 前列腺2D分割模型
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/intelligent", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "智能分析", notes = "智能分析")
    public String intelligent() throws Exception {
        return "/bus/busintelligentguidance/intelligent";
    }

    /**
     * 前列腺图像融合模型
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/intelligent1", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "智能分析", notes = "智能分析")
    public String intelligent1() throws Exception {
        return "/bus/busintelligentguidance/intelligent1";
    }
    /**
     * 前列腺多区域分割
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/intelligent2", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "智能分析", notes = "智能分析")
    public String intelligent2() throws Exception {
        return "/bus/busintelligentguidance/intelligent2";
    }


    /**
     * 前列腺配准模型
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/intelligent3", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "智能分析", notes = "智能分析")
    public String intelligent3() throws Exception {
        return "/bus/busintelligentguidance/intelligent3";
    }
    /**
     * 前列腺3D分割模型
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/intelligent4", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "智能分析", notes = "智能分析")
    public String intelligent4() throws Exception {
        return "/bus/busintelligentguidance/intelligent4";
    }
    /**
     * 肺部诊断模型
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/intelligent5", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "智能分析", notes = "智能分析")
    public String intelligent5() throws Exception {
        return "/bus/busintelligentguidance/intelligent5";
    }
    /**
     /**
     * T2补全DWI模型
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/intelligent6", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "智能分析", notes = "智能分析")
    public String intelligent6() throws Exception {
        return "/bus/busintelligentguidance/intelligent6";
    }
    /**
     * 药物推荐系统
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/intelligent7", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "智能分析", notes = "智能分析")
    public String intelligent7() throws Exception {
        return "/bus/busintelligentguidance/intelligent7";
    }

    /**
     * 多序列预测
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/intelligent8", method = RequestMethod.GET)
    @ApiImplicitParams({ @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String") })
    @ApiOperation(value = "智能分析", notes = "智能分析")
    public String intelligent8() throws Exception {
        return "/bus/busintelligentguidance/intelligent8";
    }
    /**
     *
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
        return "/bus/busintelligentguidance/edit";
    }

    /**
     * auther： qwt
     * create： 2023-06-15 09:41:48
     * describe： 获取病种信息统计详情
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getIllnessStatistics")
    @ApiOperation(value = "获取病种信息统计详情", notes = "获取病种信息统计详情")
    @ResponseBody
    private OwnResult<List<IllnessStatisticsDto>> getIllnessStatistics(@RequestBody IllnessStatisticsParam param) throws Exception {
        return busIntelligentGuidanceService.getIllnessStatistics(param);
    }

    /**
    * auther： qwt
    * create： 2023-11-15 11:56:00
    * describe： 影像图片智能分析
    */
    @RequestMapping(method = RequestMethod.POST, value = "/intelligentImageAnalysi")
    @ApiOperation(value="影像图片智能分析", notes="影像图片智能分析")
    @ResponseBody
    private OwnResult<String> intelligentImageAnalysi(@RequestBody IntelligentImageAnalysiParam param) throws Exception {
        return busIntelligentGuidanceService.intelligentImageAnalysi(param);
    }
}