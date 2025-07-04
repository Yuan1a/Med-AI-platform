package com.graphy.platform.controller;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.ui.Model;
import cn.hutool.core.util.StrUtil;
import com.graphy.db.entity.TbBusPortraitReportImagesEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.BusPortraitReportImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

/**
* @auther qwt
* @create 2022-12-05 10:22:14
* @describe 影像报告图片管理
*/
@Data
@Controller("com.graphy.platform.controller.busportraitreportimagescontroller")
@RequiredArgsConstructor
@Api(tags = "影像报告图片管理")
@RequestMapping("/pf/BusPortraitReportImages")
@Slf4j
public class BusPortraitReportImagesController   {

    private final BusPortraitReportImagesService busPortraitReportImagesService;

}