package com.graphy.service.service.impl;

import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformDbUpPageParam;

import com.graphy.service.service.PlatformCommonService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformDbUpMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbPlatformDbUpMapper;
import com.graphy.db.entity.TbPlatformDbUpEntity;
import com.graphy.service.service.PlatformDbUpService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @auther lsd
 * @create 2021-08-07 20:24:54
 * @describe 数据日志
 */
@Service("com.graphy.service.service.impl.platformdbupimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformDbUpImpl extends ServiceImpl<TbPlatformDbUpMapper, TbPlatformDbUpEntity> implements PlatformDbUpService {

    private final PlatformDbUpMapper platformDbUpMapper;
    private final PlatformCommonService platformCommonService;

    /**
     * auther： lsd
     * create： 2021-08-07 23:08:32
     * describe： 分页获取记录
     *
     * @param param 参数
     * @return
     */
    public OwnResult<OwnPageResult<TbPlatformDbUpEntity>> getPlatformDbUpPage(PlatformDbUpPageParam param) throws Exception {
        IPage<TbPlatformDbUpEntity> page = platformDbUpMapper.getPlatformDbUpPage(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    /**
     * 清除过期数据
     *
     * @throws Exception
     */
    /**
     * 清除过期数据
     *
     * @throws Exception
     */
    public OwnResult<Boolean> cleanData() throws Exception {
        try {
            platformDbUpMapper.cleanData();
            return OwnResult.result(true);
        } catch (Exception err) {
            log.error(err.toString());
            return OwnResult.error(err.toString());
        }
    }

}