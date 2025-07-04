package com.graphy.platform.controller;

import com.graphy.service.service.PlatformCommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * @auther lsd
 * @create 2021-07-22 00:47:45
 * @describe 图片管理
 */
@Data
@Controller("com.graphy.platform.controller.platformimagecontroller")
@RequiredArgsConstructor
@Api(tags = "图片管理")
@RequestMapping("/pf/PlatformImage")
@Slf4j
public class PlatformImageController {

    private final PlatformCommonService platformCommonService;

    /**
     * 请求图片
     *
     * @return
     */
    @RequestMapping(value = "/load/{year}/{month}/{day}/{fileId}.{suffix}", method = RequestMethod.GET)
    @ApiOperation(value = "请求图片", notes = "请求图片")
    private void load(
            @PathVariable("year") String year,
            @PathVariable("month") String month,
            @PathVariable("day") String day,
            @PathVariable("fileId") String fileId,
            @PathVariable("suffix") String suffix,
            HttpServletResponse response) throws Exception {
        String filePath = "/" + year + "/" + month + "/" + day + "/" + fileId + "." + suffix;
        platformCommonService.imageLoad(filePath, response);
    }

}