package com.graphy.service.service.impl;

import com.graphy.service.bean.OwnResult;
import com.graphy.config.BaseConfig;
import com.graphy.unit.redis.attr.ReidsKeyInfo;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformRedisMapper;
import com.graphy.service.service.PlatformRedisService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @auther lsd
 * @create 2021-07-29 08:24:24
 * @describe 缓存管理
 */
@Service("com.graphy.service.service.impl.platformredisimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformRedisImpl implements PlatformRedisService {

    private final PlatformRedisMapper platformRedisMapper;

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 获取缓存信息
     *
     * @param key 关键字
     * @return
     */
    public OwnResult<List<ReidsKeyInfo>> getRedisRecord(String key, Integer count) throws Exception {
        if (count == null) count = 20;
        List<ReidsKeyInfo> list = com.graphy.unit.redis.Api.findKeyInfo(BaseConfig.PRO_KEY, key, count);
        return OwnResult.result(list);
    }


}