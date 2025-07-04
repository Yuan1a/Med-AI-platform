package com.graphy.platform.controller;

import cn.hutool.core.util.StrUtil;

import com.graphy.db.entity.TbBusUnitEntity;
import com.graphy.db.entity.TbPlatformConfigEntity;
import com.graphy.service.bean.OwnUserInfo;
import com.graphy.service.bean.dto.PlatformIndexModuleDto;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.dto.PlatformUserDto;
import com.graphy.db.entity.TbPlatformUserEntity;
import com.graphy.db.service.TbPlatformUserService;
import com.graphy.config.BaseConfig;
import com.graphy.service.bean.dto.PlatformUserPageDto;
import com.graphy.service.bean.param.PlatformUserPageParam;
import com.graphy.service.service.PlatformCommonService;
import com.graphy.service.service.PlatformUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformIndexService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @auther lsd
 * @create 2021-07-22 00:45:16
 * @describe 平台首页
 */
@Data
@Controller("com.graphy.platform.controller.platformindexcontroller")
@RequiredArgsConstructor
@Api(tags = "平台首页")
@RequestMapping("/pf/PlatformIndex")
@Slf4j
public class PlatformIndexController {
    @Value("${server.servlet.context-path}")
    private String webLevel;
    private final PlatformIndexService platformIndexService;
    private final PlatformUserService platformUserService;
    private final TbPlatformUserService tbPlatformUserService;
    private final PlatformCommonService platformCommonService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String index(Model model, @RequestAttribute(name = "user", required = false) PlatformUserDto user) throws Exception {


        TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
        model.addAttribute("ce", configEntity);
        Boolean coerceUpdatePassword = false;
        TbPlatformUserEntity userEntity = tbPlatformUserService.getById(user.getUserId());
        if (StrUtil.hasEmpty(userEntity.getUpdatePassword()) || !userEntity.getUpdatePassword().equals("1") || userEntity.getExpirePassword().before(new Date())) {
            coerceUpdatePassword = true;
        }
        if (StrUtil.hasEmpty(userEntity.getPic())) {

            if (StrUtil.hasEmpty(userEntity.getGender()) || userEntity.getGender().equals("1")) {
                userEntity.setPic(webLevel+"/image/head/user1.png");
            } else {
                userEntity.setPic(webLevel+"/image/head/user2.png");
            }
        } else {
            userEntity.setPic(webLevel+"/pf/PlatformImage/load" + userEntity.getPic());
        }
        model.addAttribute("coerceUpdatePassword", coerceUpdatePassword ? "1" : "0");
        model.addAttribute("user", userEntity);
        model.addAttribute("unitId", userEntity.getUnit());
        model.addAttribute("tokenName", BaseConfig.PRO_KEY + "_" + BaseConfig.TOKEN_PLATFORM);
        return "/platform/index/index";
    }

    /**
     * 个人信息编辑
     *
     * @return
     */
    @RequestMapping(value = "/editUserInfoPage", method = RequestMethod.GET)
    private String editUserInfoPage(Model model, @RequestAttribute(name = "user", required = false) PlatformUserDto user) throws Exception {
        PlatformUserPageParam userPageParam = new PlatformUserPageParam();
        userPageParam.setSize(1L);
        userPageParam.setPage(1L);
        userPageParam.setId(user.getUserId());
        PlatformUserPageDto userEntity = platformUserService.getUserPage(userPageParam).getResult().getRecords().get(0);
        model.addAttribute("user", userEntity);
        return "/platform/index/edit-user-info";
    }

    /**
     * 修改密码页面
     *
     * @return
     */
    @RequestMapping(value = "/upPasswordPage", method = RequestMethod.GET)
    private String upPasswordPage(Model model) throws Exception {
        String passwordLevelDescribe = "";
        TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
        if (configEntity.getPlatformSafetyPasswordLevel().equals(2)) {
            passwordLevelDescribe += ",并包含数字,字母";
        } else if (configEntity.getPlatformSafetyPasswordLevel().equals(3)) {
            passwordLevelDescribe += ",并包含数字,字母,特殊字符";
        } else if (configEntity.getPlatformSafetyPasswordLevel().equals(4)) {
            passwordLevelDescribe += ",并包含数字,大写字母,小写字符,特殊字符";
        }
        model.addAttribute("passwordLevelDescribe", passwordLevelDescribe);
        model.addAttribute("defPasswordMinLength", configEntity.getPlatformSafetyPasswordMinLength());
        return "/platform/index/up-password";
    }

    /**
     * 无原始密码修改页面
     *
     * @return
     */
    @RequestMapping(value = "/upPasswordDirectPage", method = RequestMethod.GET)
    private String upPasswordDirectPage(Model model) throws Exception {
        String passwordLevelDescribe = "";
        TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
        if (configEntity.getPlatformSafetyPasswordLevel().equals(2)) {
            passwordLevelDescribe += ",并包含数字,字母";
        } else if (configEntity.getPlatformSafetyPasswordLevel().equals(3)) {
            passwordLevelDescribe += ",并包含数字,字母,特殊字符";
        } else if (configEntity.getPlatformSafetyPasswordLevel().equals(4)) {
            passwordLevelDescribe += ",并包含数字,大写字母,小写字符,特殊字符";
        }
        model.addAttribute("passwordLevelDescribe", passwordLevelDescribe);
        model.addAttribute("defPasswordMinLength", configEntity.getPlatformSafetyPasswordMinLength());
        return "/platform/index/up-password-direct";
    }

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 原始密码修改
     */
    @RequestMapping(method = RequestMethod.POST, value = "/upPasswordDirect")
    @ApiOperation(value = "原始密码修改", notes = "原始密码修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "新密码", value = "password", required = true, dataType = "String"), @ApiImplicitParam(name = "确认密码", value = "confirmPassword", required = true, dataType = "String")})
    @ResponseBody
    private OwnResult<Boolean> upPasswordDirect(String password, String confirmPassword, @RequestAttribute(name = "user", required = false) PlatformUserDto user) throws Exception {
        return platformIndexService.upPasswordDirect(user.getUserId(), password, confirmPassword);
    }

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 保存修改密码
     */
    @RequestMapping(method = RequestMethod.POST, value = "/upPassword")
    @ApiOperation(value = "保存修改密码", notes = "保存修改密码")
    @ApiImplicitParams({@ApiImplicitParam(name = "原始密码", value = "oldPassword", required = true, dataType = "String"), @ApiImplicitParam(name = "新密码", value = "password", required = true, dataType = "String"), @ApiImplicitParam(name = "确认密码", value = "confirmPassword", required = true, dataType = "String")})
    @ResponseBody
    private OwnResult<Boolean> upPassword(String oldPassword, String password, String confirmPassword, @RequestAttribute(name = "user", required = false) PlatformUserDto user) throws Exception {
        return platformIndexService.upPassword(user.getUserId(), oldPassword, password, confirmPassword);
    }

    /**
     * auther： lsd
     * create： 2021-07-27 08:34:30
     * describe： 退出系统
     */
    @RequestMapping(method = RequestMethod.POST, value = "/loginOut")
    @ApiOperation(value = "退出系统", notes = "退出系统")
    @ResponseBody
    private OwnResult<Boolean> loginOut(HttpServletRequest request) throws Exception {
        try {
            String token = com.graphy.unit.http.Api.getCookie(request, BaseConfig.PRO_KEY + "_" + BaseConfig.TOKEN_PLATFORM);
            if (!StrUtil.hasEmpty(token)) {
                com.graphy.unit.redis.Api.remove(BaseConfig.PRO_KEY, BaseConfig.TOKEN_PLATFORM + ":" + token);
            }
        } catch (Exception err) {
            log.error(err.toString());
        }
        return OwnResult.result(true);
    }

    /**
     * auther： lsd
     * create： 2021-07-27 08:34:30
     * describe： 获取用户导航权限
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getIndexModule")
    @ApiOperation(value = "获取用户导航权限", notes = "获取用户导航权限")
    @ResponseBody
    private OwnResult<List<PlatformIndexModuleDto>> getIndexModule(HttpServletRequest request) throws Exception {
        return platformIndexService.getIndexModule(request);
    }

    /**
     * auther： lsd
     * create： 2021-07-28 22:32:50
     * describe： 保存用户信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveUserInfo")
    @ApiOperation(value = "保存用户信息", notes = "保存用户信息")
    @ResponseBody
    private OwnResult<TbPlatformUserEntity> saveUserInfo(@RequestBody TbPlatformUserEntity param, @RequestAttribute(name = "user", required = false) PlatformUserDto user) throws Exception {
        param.setId(user.getUserId());
        return platformIndexService.saveUserInfo(param);
    }
}