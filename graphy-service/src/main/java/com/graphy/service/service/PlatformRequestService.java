package com.graphy.service.service;

import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformRequestPageParam;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbPlatformRequestEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther lsd
 * @create 2021-08-07 10:44:52
 * @describe 网络请求监听
 */
public interface PlatformRequestService extends IService<TbPlatformRequestEntity> {

    /**
     * 添加网络请求数据
     *
     * @param request
     * @param response
     * @param handler
     * @throws Exception
     */
    void insertRequest(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

    /**
     * auther： lsd
     * create： 2021-08-07 23:11:13
     * describe： 分页获取数据
     *
     * @param param 参数
     * @return
     */
    OwnResult<OwnPageResult<TbPlatformRequestEntity>> getPlatformRequestPage(PlatformRequestPageParam param) throws Exception;

    /**
     * 清除过期数据
     *
     * @throws Exception
     */
    OwnResult<Boolean> cleanData() throws Exception;
}