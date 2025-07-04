package com.graphy.service.service.impl;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformLoginErrorsParam;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformLoginErrorMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbPlatformLoginErrorMapper;
import com.graphy.db.entity.TbPlatformLoginErrorEntity;
import com.graphy.service.service.PlatformLoginErrorService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @auther lsd
 * @create 2021-07-29 21:25:29
 * @describe 登录错误
 */
@Service("com.graphy.service.service.impl.platformloginerrorimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformLoginErrorImpl extends ServiceImpl<TbPlatformLoginErrorMapper, TbPlatformLoginErrorEntity> implements PlatformLoginErrorService {

    private final PlatformLoginErrorMapper platformLoginErrorMapper;

    /**
    * auther： lsd
    * create： 2021-07-29 21:30:09
    * describe： 分页获取登录错误日志
    * @param param   参数
    * @return
    */
    public OwnResult<OwnPageResult<TbPlatformLoginErrorEntity>> getLoginErrors(PlatformLoginErrorsParam param) throws Exception {
        IPage<TbPlatformLoginErrorEntity> page = platformLoginErrorMapper.getLoginErrors(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

}