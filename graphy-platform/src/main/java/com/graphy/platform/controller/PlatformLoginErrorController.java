package com.graphy.platform.controller;

import com.graphy.db.entity.TbPlatformLoginErrorEntity;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformLoginErrorsParam;

import com.graphy.service.service.PlatformCommonService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformLoginErrorService;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther lsd
 * @create 2021-07-29 21:25:29
 * @describe 登录错误
 */
@Data
@Controller("com.graphy.platform.controller.platformloginerrorcontroller")
@RequiredArgsConstructor
@Api(tags = "登录错误")
@RequestMapping("/pf/PlatformLoginError")
@Slf4j
public class PlatformLoginErrorController {

    private final PlatformLoginErrorService platformLoginErrorService;
    private final PlatformCommonService platformCommonService;
    /**
     * 登录错误首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "登录错误首页", notes = "登录错误首页")
    private String index() throws Exception {
        return "/platform/login-error/index";
    }

    /**
     * auther： lsd
     * create： 2021-07-29 21:30:09
     * describe： 分页获取登录错误日志
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getLoginErrors")
    @ApiOperation(value = "分页获取登录错误日志", notes = "分页获取登录错误日志")
    @ResponseBody
    private OwnResult<OwnPageResult<TbPlatformLoginErrorEntity>> getLoginErrors(@RequestBody PlatformLoginErrorsParam param) throws Exception {
        return platformLoginErrorService.getLoginErrors(param);
    }
}