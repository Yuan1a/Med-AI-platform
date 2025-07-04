package com.graphy.platform.controller;

import com.graphy.service.bean.dto.PlatformLoginLogsDto;

import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformLoginLogsParam;
import com.graphy.service.service.PlatformCommonService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformLoginLogService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther lsd
 * @create 2021-07-28 23:15:04
 * @describe 登录日志
 */
@Data
@Controller("com.graphy.platform.controller.platformloginlogcontroller")
@RequiredArgsConstructor
@Api(tags = "登录日志")
@RequestMapping("/pf/PlatformLoginLog")
@Slf4j
public class PlatformLoginLogController {

    private final PlatformLoginLogService platformLoginLogService;
    private final PlatformCommonService platformCommonService;
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/platform/login-log/index";
    }

    /**
     * auther： lsd
     * create： 2021-07-28 23:27:14
     * describe： 分页获取登录信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getLoginLogs")
    @ApiOperation(value = "分页获取登录信息", notes = "分页获取登录信息")
    @ResponseBody
    private OwnResult<OwnPageResult<PlatformLoginLogsDto>> getLoginLogs(@RequestBody PlatformLoginLogsParam param) throws Exception {
        return platformLoginLogService.getLoginLogs(param);
    }
}