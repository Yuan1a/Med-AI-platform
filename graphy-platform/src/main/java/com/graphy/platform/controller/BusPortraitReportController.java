package com.graphy.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.db.entity.*;
import com.graphy.db.service.TbBusPatientService;
import com.graphy.db.service.TbBusUnitService;
import com.graphy.db.service.TbPlatformUserService;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnUserInfo;
import com.graphy.service.bean.dto.OnChainDataDto;
import com.graphy.service.bean.param.PortraitReportRecordParam;
import com.graphy.service.bean.param.SavePortraitReportRecordParam;
import com.graphy.service.bean.dto.PortraitReportRecordDto;
import com.graphy.service.bean.OwnResult;
import cn.hutool.core.util.StrUtil;
import com.graphy.service.service.BusPortraitReportImagesService;
import com.graphy.service.service.PlatformCommonService;
import com.graphy.unit.OwnCommon;
import com.graphy.unit.OwnValueText;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.BusPortraitReportService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 11:22:46
 * @describe 影像报告
 */
@Data
@Controller("com.graphy.platform.controller.busportraitreportcontroller")
@RequiredArgsConstructor
@Api(tags = "影像报告")
@RequestMapping("/pf/BusPortraitReport")
@Slf4j
public class BusPortraitReportController {

    private final BusPortraitReportService busPortraitReportService;
    private final TbBusPatientService tbBusPatientService;
    private final TbBusUnitService tbBusUnitService;
    private final BusPortraitReportImagesService busPortraitReportImagesService;
    @Value("${pro.file-folder}")
    private String fileFolder;
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
        String unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>()
                .eq(TbBusUnitEntity::getStatus, "1")
        ), "id", "name"));
        model.addAttribute("unit", unit);
        return "/bus/portraitreport/index";
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
        TbBusPortraitReportEntity orm = new TbBusPortraitReportEntity();
        String patientName = "";

        if (!StrUtil.hasEmpty(id)) {
            orm = busPortraitReportService.getById(id);

        } else {

        }
        model.addAttribute("orm", orm);
        return "/bus/portraitreport/edit";
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:48:05
     * describe： 获取记录
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getPortraitReportRecord")
    @ApiOperation(value = "获取记录", notes = "获取记录")
    @ResponseBody
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "主键", dataType = "String"), @ApiImplicitParam(name = "patientId", value = "患者ID", dataType = "String"), @ApiImplicitParam(name = "doctor", value = "报告医生", dataType = "String"), @ApiImplicitParam(name = "reportTimeStart", value = "报告时间-开始", dataType = "Date"), @ApiImplicitParam(name = "reportTimeEnd", value = "报告时间-截止", dataType = "Date"), @ApiImplicitParam(name = "patientName", value = "患者姓名", dataType = "String")})
    private OwnResult<OwnPageResult<PortraitReportRecordDto>> getPortraitReportRecord(PortraitReportRecordParam param) throws Exception {
        return busPortraitReportService.getPortraitReportRecord(param);
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:48:37
     * describe： 保存信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/savePortraitReportRecord")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ResponseBody
    private OwnResult<Boolean> savePortraitReportRecord(@RequestBody SavePortraitReportRecordParam param) throws Exception {
        return busPortraitReportService.savePortraitReportRecord(param);
    }

    /**
     * auther： lsd
     * create： 2022-03-01 12:48:55
     * describe： 删除信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delPortraitReportRecord")
    @ApiOperation(value = "删除信息", notes = "删除信息")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "String")
    })
    private OwnResult<Boolean> delPortraitReportRecord(String id) throws Exception {
        return busPortraitReportService.delPortraitReportRecord(id);
    }
    /**
     * auther： qwt
     * create： 2022-12-01 08:48:55
     * describe： 获取影像报告图片
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getImages")
    @ApiOperation(value = "获取影像报告图片", notes = "获取影像报告图片")
    @ResponseBody
    private OwnResult<List<String>> getImages(String id) throws Exception {
        List<String> imagesIdList=new ArrayList<>();
        List<String> base64List=new ArrayList<>();
        if (!StrUtil.hasEmpty(id)){

            TbBusPatientEntity tbBusPatientServiceById = tbBusPatientService.getById(id);
            OnChainDataDto onChainDataDto = JSONObject.parseObject(busPortraitReportService.blockchainImageExtraction(tbBusPatientServiceById.getId()), OnChainDataDto.class);
            imagesIdList = onChainDataDto.getImagesIdList();
            if (!OwnCommon.isNullOrEmpty(imagesIdList)){
                for (String imagesId:imagesIdList ){
                    TbBusPortraitReportImagesEntity imagesServiceById = busPortraitReportImagesService.getById(imagesId);
                    byte[] bytes = com.graphy.unit.file.Api.readFileByte(fileFolder+imagesServiceById.getImagePath());
                    BufferedImage bufferedImage = com.graphy.unit.image.Api.bytesToImage(bytes);
                    String base64 = com.graphy.unit.image.Api.imageToBase64(bufferedImage,imagesServiceById.getImageFormat());
                    base64List.add(base64);
                }
            }
        }
        return OwnResult.result(base64List);
    }
}