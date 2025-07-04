package com.graphy.platform.controller;

import com.graphy.service.bean.dto.PlatformUserDto;
import com.graphy.service.service.PlatformCommonService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformFirstPageService;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther lsd
 * @create 2021-07-27 17:28:34
 * @describe 主页
 */
@Data
@Controller("com.graphy.platform.controller.platformfirstpagecontroller")
@RequiredArgsConstructor
@Api(tags = "主页")
@RequestMapping("/pf/PlatformFirstPage")
@Slf4j
public class PlatformFirstPageController {

    private final PlatformFirstPageService platformFirstPageService;
    private final PlatformCommonService platformCommonService;
    /**
     * 主页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String index(Model model, @RequestAttribute(name = "user", required = false) PlatformUserDto user) throws Exception {
        return "/platform/first-page/index";
    }


}