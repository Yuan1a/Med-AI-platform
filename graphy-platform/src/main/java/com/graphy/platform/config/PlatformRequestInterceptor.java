package com.graphy.platform.config;

import cn.hutool.core.util.StrUtil;
import com.graphy.db.entity.TbPlatformConfigEntity;
import com.graphy.service.bean.OwnErrorInfo;
import com.graphy.service.bean.OwnResult;

import com.graphy.service.bean.dto.PlatformUserDto;
import com.graphy.config.BaseConfig;
import com.graphy.service.service.PlatformCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Set;

/**
 * 请求拦截
 */
@Component("com.graphy.platform.config.requestinterceptor")
public class PlatformRequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PlatformCommonService platformCommonService;
    @Value("${server.servlet.context-path}")
    private String webLevel;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String tokenName = BaseConfig.PRO_KEY + "_" + BaseConfig.TOKEN_PLATFORM;
        String token = com.graphy.unit.http.Api.getCookie(request, tokenName);
        if (StrUtil.hasEmpty(token)) {
            token = request.getHeader(tokenName);
        }


        if (StrUtil.hasEmpty(token)) {
            redirect(new OwnErrorInfo("1", ""), request, response);
            return false;
        }
        PlatformUserDto user = com.graphy.unit.redis.Api.get(BaseConfig.PRO_KEY,
                BaseConfig.TOKEN_PLATFORM + ":" + token,
                PlatformUserDto.class);
        if (user == null) {
            redirect(new OwnErrorInfo("1", ""), request, response);
            return false;
        }
        Boolean hasPower = false;
        Set<String> set = user.getUserPowerModule();
        for (String upm : set) {
            if (requestURI.toLowerCase().startsWith(webLevel +upm + "/")) {
                hasPower = true;
                break;
            }
        }
        if (!hasPower) {
            redirect(new OwnErrorInfo("2", "请联系管理员为您开通【" + requestURI + "】的访问权限"), request, response);
            return false;
        }

        //更新令牌的过期时间
        TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
        com.graphy.unit.redis.Api.upTimeOut(
                BaseConfig.PRO_KEY,
                BaseConfig.TOKEN_PLATFORM + ":" + token,
                com.graphy.unit.date.Api.dateAddSecond(new Date(), configEntity.getPlatformSafetyTokenOutTime()));
        request.setAttribute("user", user);
        return super.preHandle(request, response, handler);
    }

    /**
     * 重新跳转页面
     *
     * @param errorInfo 异常对象
     * @param request   请求对象
     * @param response  响应对象
     * @throws Exception
     */
    private void redirect(OwnErrorInfo errorInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //令牌过期
        if (errorInfo.getErrorType().equals("1")) {
            if (request.getMethod().toLowerCase().equals("get")) {
                request.setAttribute("error", errorInfo);
                request.getRequestDispatcher("/pf/PlatformError/index").forward(request, response);
            } else {
                OwnResult<String> result = OwnResult.error("令牌已过期");
                result.setCode("-1000");
                com.graphy.unit.http.Api.responseObj(response, result);
            }
        }
        //无权限访问
        else if (errorInfo.getErrorType().equals("2")) {
            if (request.getMethod().toLowerCase().equals("get")) {
                request.setAttribute("error", errorInfo);
                request.getRequestDispatcher("/pf/PlatformError/index").forward(request, response);
            } else {
                com.graphy.unit.http.Api.responseObj(response, OwnResult.error(errorInfo.getErrorInfo()));
            }
        }
    }


}
