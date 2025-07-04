package com.graphy.service.service.impl;

import java.util.Date;

import cn.hutool.core.util.StrUtil;
import com.graphy.db.entity.TbPlatformConfigEntity;
import com.graphy.service.bean.OwnResult;

import com.graphy.service.bean.dto.PlatformUserDto;
import com.graphy.service.bean.dto.PlatformRolePowerDto;
import com.graphy.service.bean.param.PlatformRolePowerParam;
import com.graphy.db.entity.TbPlatformLoginEntity;
import com.graphy.db.entity.TbPlatformLoginErrorEntity;
import com.graphy.db.mapper.TbPlatformLoginErrorMapper;
import com.graphy.db.mapper.TbPlatformLoginMapper;
import com.graphy.config.BaseConfig;
import com.graphy.service.mapper.PlatformRoleMapper;
import com.graphy.service.service.PlatformCommonService;
import com.graphy.unit.OwnCommon;
import com.graphy.unit.verifycode.attr.VerifyCodeInfo;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformLoginMapper;
import com.graphy.service.service.PlatformLoginService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @auther lsd
 * @create 2021-07-22 00:13:07
 * @describe 登录管理
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PlatformLoginImpl implements PlatformLoginService {

    private final PlatformLoginMapper platformLoginMapper;
    private final PlatformRoleMapper platformRoleMapper;
    private final TbPlatformLoginErrorMapper tbPlatformLoginErrorMapper;
    private final TbPlatformLoginMapper tbPlatformLoginMapper;
    private final PlatformCommonService platformCommonService;

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
    public OwnResult<String> login(String account,
                                   String password,
                                   String code,
                                   HttpServletRequest request,
                                   HttpServletResponse response
    ) throws Exception {
        String sessionKey = null;
        String sessionId = null;
        try {
            TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
            if (StrUtil.hasEmpty(account)) throw new Exception("请输入用户名");
            if (StrUtil.hasEmpty(password)) throw new Exception("请输入密码");
            if (StrUtil.hasEmpty(code) && configEntity.getPlatformSafetyIdeCodeIsUse().equals("1")) throw new Exception("请输入验证码");
            sessionId = com.graphy.unit.http.Api.getSessionId(request, BaseConfig.PRO_KEY + "_" + BaseConfig.SESSION_NAME);
            if (StrUtil.hasEmpty(sessionId)) throw new Exception("无效的会话ID");
            sessionKey = BaseConfig.VERIFYCODE_NAME + sessionId;
            String verifyCode = com.graphy.unit.redis.Api.get(BaseConfig.PRO_KEY, sessionKey);
            if (configEntity.getPlatformSafetyIdeCodeIsUse().equals("1")&&StrUtil.hasEmpty(verifyCode)) throw new Exception("验证码已失效");
            if (configEntity.getPlatformSafetyIdeCodeIsUse().equals("1")&&!verifyCode.equals(code)) throw new Exception("验证码错误");
            String cPassword = com.graphy.unit.crypt.Api.encrypt(password, BaseConfig.CRYPT_KEY);
            PlatformUserDto user = platformLoginMapper.login(account, cPassword);
            if (user == null) throw new Exception("用户名或密码错误");
            Set<String> userPowerModule = new HashSet<>();
            userPowerModule.add("/pf/PlatformIndex".toLowerCase());
            userPowerModule.add("/pf/PlatformImage".toLowerCase());
            if (!StrUtil.hasEmpty(user.getUserRoleId())) {
                PlatformRolePowerParam powerParam = new PlatformRolePowerParam();
                powerParam.setRoleId(user.getUserRoleId());
                List<PlatformRolePowerDto> platformRolePowerDtos = platformRoleMapper.getRolePower(powerParam);
                for (PlatformRolePowerDto item : platformRolePowerDtos) {
                    String controller = item.getController().toLowerCase();
                    if (item.getCheck() && !StrUtil.hasEmpty(controller) && !controller.equals("#")) {
                        if (!userPowerModule.contains(controller)) {
                            userPowerModule.add(controller);
                        }
                    }
                }
                user.setUserPowerModule(userPowerModule);
            }


            String token = OwnCommon.createNowToken(configEntity.getPlatformSafetyTokenLevel() * 20) + OwnCommon.getUUID();
            com.graphy.unit.redis.Api.add(BaseConfig.PRO_KEY, BaseConfig.TOKEN_PLATFORM + ":" + token, user, com.graphy.unit.date.Api.dateAddSecond(new Date(), configEntity.getPlatformSafetyTokenOutTime()));
            com.graphy.unit.http.Api.setCookie(response, BaseConfig.PRO_KEY + "_" + BaseConfig.TOKEN_PLATFORM, token);
            //记录登录日志
            TbPlatformLoginEntity loginEntity = new TbPlatformLoginEntity();
            loginEntity.setUserId(user.getUserId());
            loginEntity.setToken(token);
            loginEntity.setSessionId(sessionId);
            loginEntity.setIp(com.graphy.unit.http.Api.getIpAddr(request));
            tbPlatformLoginMapper.insert(loginEntity);
            return OwnResult.result(token);
        } catch (Exception err) {
            //记录登录错误日志
            TbPlatformLoginErrorEntity errorEntity = new TbPlatformLoginErrorEntity();
            errorEntity.setSessionId(sessionId);
            errorEntity.setIp(com.graphy.unit.http.Api.getIpAddr(request));
            errorEntity.setAccount(account);
            errorEntity.setPassword(password);
            errorEntity.setVerifyCode(code);
            errorEntity.setError(err.toString());
            errorEntity.setTime(new Date());
            tbPlatformLoginErrorMapper.insert(errorEntity);
            return OwnResult.error(err.getMessage());
        } finally {
            if (!StrUtil.hasEmpty(sessionKey)) {
                com.graphy.unit.redis.Api.remove(BaseConfig.PRO_KEY, sessionKey);
            }
        }
    }

    /**
     * 发送验证码
     *
     * @param request
     * @param response
     */
    public void sendVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sessionId = com.graphy.unit.http.Api.getSessionId(request, BaseConfig.PRO_KEY + "_" + BaseConfig.SESSION_NAME);
        VerifyCodeInfo codeInfo = com.graphy.unit.verifycode.Api.getVerifyCode();
        String sessionKey = BaseConfig.VERIFYCODE_NAME + sessionId;
        TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
        com.graphy.unit.redis.Api.add(BaseConfig.PRO_KEY, sessionKey, codeInfo.getCode(), com.graphy.unit.date.Api.dateAddSecond(new Date(), configEntity.getPlatformSafetyIdeCodeOutTime()));
        com.graphy.unit.http.Api.responseImage(response, codeInfo.getImage(), "png");
    }


}