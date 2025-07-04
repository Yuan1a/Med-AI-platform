package com.graphy.platform.controller;

import com.graphy.service.bean.OwnErrorInfo;
import com.graphy.service.service.PlatformCommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @auther lsd
 * @create 2021-07-22 00:47:45
 * @describe 字典管理
 */
@Data
@Controller("com.graphy.platform.controller.platformerrorcontroller")
@RequiredArgsConstructor
@Api(tags = "错误信息管理")
@RequestMapping("/pf/PlatformError")
@Slf4j
public class PlatformErrorController {
    private final PlatformCommonService platformCommonService;
    /**
     * 错误信息页面
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "错误信息页面", notes = "错误信息页面")
    private String index(
            @RequestAttribute(name = "error", required = true) OwnErrorInfo error,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        model.addAttribute("error", error);
        return "/error/index";
    }

}