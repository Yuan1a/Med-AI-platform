package com.graphy.service.service;

import com.graphy.service.bean.OwnResult;
import com.graphy.unit.redis.attr.ReidsKeyInfo;

import java.util.List;

/**
 * @auther lsd
 * @create 2021-07-29 08:24:24
 * @describe 缓存管理
 */
public interface PlatformRedisService {

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 获取缓存信息
     *
     * @param key   关键字
     * @param count 限制数量
     * @return
     */
    OwnResult<List<ReidsKeyInfo>> getRedisRecord(String key, Integer count) throws Exception;
}