package com.graphy.service.service;

import com.graphy.service.bean.OwnResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther lsd
 * @create 2021-07-22 00:13:07
 * @describe 登录管理
 */
public interface PlatformLoginService {

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
    OwnResult<String> login(String account, String password, String code, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 发送验证码
     *
     * @param request
     * @param response
     */
    void sendVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception;

}