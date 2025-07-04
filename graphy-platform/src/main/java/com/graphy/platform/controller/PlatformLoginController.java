package com.graphy.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.graphy.db.entity.TbPlatformConfigEntity;
import com.graphy.service.bean.OwnResult;

import com.graphy.config.BaseConfig;
import com.graphy.service.service.PlatformCommonService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformLoginService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther lsd
 * @create 2021-07-22 00:13:07
 * @describe 登录管理
 */
@Data
@Controller("com.graphy.platform.controller.platformlogincontroller")
@RequiredArgsConstructor
@Api(tags = "登录管理")
@RequestMapping("/pf/PlatformLogin")
@Slf4j
public class PlatformLoginController {

    private final PlatformLoginService platformLoginService;
    private final PlatformCommonService platformCommonService;

    /**
     * 初始化一个标识用户唯一性的会话ID
     *
     * @param request
     * @param response
     */
    private void initSessionId(HttpServletRequest request, HttpServletResponse response) {
        String sessionName = BaseConfig.PRO_KEY + "_" + BaseConfig.SESSION_NAME;
        if (StrUtil.hasEmpty(com.graphy.unit.http.Api.getCookie(request, sessionName))) {
            com.graphy.unit.http.Api.initSessionId(request, response, sessionName);
        }
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login-page", method = RequestMethod.GET)
    private String login(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model) throws Exception {
        initSessionId(request, response);
        TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
        model.addAttribute("ce", configEntity);
        return "/login";
    }

    /**
     * 获取验证码
     * <p>创建人：林诗达</p>
     * <p>创建时间：2019-12-06 16:13:14</p>
     *
     * @return
     */
    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        platformLoginService.sendVerifyCode(request, response);
    }

    /**
     * 登录系统
     *
     * @param account  账户
     * @param password 密码
     * @param code     验证码
     * @param request  请求对象
     * @param response 响应对象
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public OwnResult<String> login(String account, String password, String code,
                                   HttpServletRequest request,
                                   HttpServletResponse response
    ) throws Exception {
        return platformLoginService.login(account, password, code, request, response);
    }


}