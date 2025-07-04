package com.graphy.service.service.impl;

import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformRequestPageParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Enumeration;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.graphy.db.entity.TbPlatformConfigEntity;
import com.graphy.service.service.PlatformCommonService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformRequestMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbPlatformRequestMapper;
import com.graphy.db.entity.TbPlatformRequestEntity;
import com.graphy.service.service.PlatformRequestService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @auther lsd
 * @create 2021-08-07 10:44:52
 * @describe 网络请求监听
 */
@Service("com.graphy.service.service.impl.platformrequestimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformRequestImpl extends ServiceImpl<TbPlatformRequestMapper, TbPlatformRequestEntity> implements PlatformRequestService {

    private final PlatformRequestMapper platformRequestMapper;

    private final PlatformCommonService platformCommonService;

    /**
     * 添加网络请求数据
     *
     * @param request
     * @param response
     * @param handler
     * @throws Exception
     */
    public void insertRequest(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String uri = request.getRequestURI();
            if (!uri.contains(".")) {
                String url = request.getRequestURL().toString();
                TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
                if (configEntity != null && !StrUtil.hasEmpty(configEntity.getRequestLogOpen()) && configEntity.getRequestLogOpen().equals("1")) {
                    TbPlatformRequestEntity requestEntity = new TbPlatformRequestEntity();
                    requestEntity.setIp(com.graphy.unit.http.Api.getIpAddr(request));
                    requestEntity.setUrl(url);
                    if (!StrUtil.hasEmpty(request.getQueryString())) {
                        requestEntity.setUrl(requestEntity.getUrl() + "?" + request.getQueryString());
                    }
                    requestEntity.setMode(request.getMethod());
                    if (request.getParameterMap() != null) {
                        requestEntity.setParameterMap(JSONObject.toJSONString(request.getParameterMap()));
                    }
                    if (request.getHeaderNames() != null) {
                        Enumeration er = request.getHeaderNames();
                        StringBuilder sb = new StringBuilder();
                        while (er.hasMoreElements()) {
                            String name = (String) er.nextElement();
                            String value = request.getHeader(name);
                            if (sb.toString().equals("")) {
                                sb.append(name + ":" + value);
                            } else {
                                sb.append("&" + name + "=" + value);
                            }
                        }
                        requestEntity.setHeader(sb.toString());
                    }
                    requestEntity.setContentType(request.getContentType());
                    if (!StrUtil.hasEmpty(request.getContentType()) && request.getContentType().toLowerCase().contains("application/json")) {
                        requestEntity.setPostData(getPostData(request));
                    }
                    requestEntity.setRecordTimeOut(com.graphy.unit.date.Api.dateAddDate(new Date(), configEntity.getRequestLogKeepTime()));
                    this.save(requestEntity);
                }
            }
        } catch (Exception err) {
            log.error("错误日志:", err);
            err.printStackTrace();
        }
    }

    public String getPostData(HttpServletRequest request) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception err) {
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (Exception err) {
                log.error(err.toString());
            }
            return sb.toString();
        }
    }

    /**
     * auther： lsd
     * create： 2021-08-07 23:11:13
     * describe： 分页获取数据
     *
     * @param param 参数
     * @return
     */
    public OwnResult<OwnPageResult<TbPlatformRequestEntity>> getPlatformRequestPage(PlatformRequestPageParam param) throws Exception {
        IPage<TbPlatformRequestEntity> page = platformRequestMapper.getPlatformRequestPage(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    /**
     * 清除过期数据
     *
     * @throws Exception
     */
    public OwnResult<Boolean> cleanData() throws Exception {
        try {
            TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
            if (configEntity != null &&
                    !StrUtil.hasEmpty(configEntity.getDbUpLogOpen())
                    && configEntity.getDbUpLogOpen().equals("1")
                    && configEntity.getRequestLogKeepTime() != null
                    && configEntity.getRequestLogKeepTime() > 0
            ) {
                platformRequestMapper.cleanData(configEntity.getRequestLogKeepTime());
            }
            return OwnResult.result(true);
        } catch (Exception err) {
            return OwnResult.error(err.toString());
        }
    }


}