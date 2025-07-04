package com.graphy.service.service.impl;
import com.graphy.service.bean.dto.PlatformLoginLogsDto;

import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformLoginLogsParam;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformLoginLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbPlatformLoginMapper;
import com.graphy.db.entity.TbPlatformLoginEntity;
import com.graphy.service.service.PlatformLoginLogService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @auther lsd
 * @create 2021-07-28 23:15:04
 * @describe 登录日志
 */
@Service("com.graphy.service.service.impl.platformloginlogimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformLoginLogImpl extends ServiceImpl<TbPlatformLoginMapper, TbPlatformLoginEntity> implements PlatformLoginLogService {

    private final PlatformLoginLogMapper platformLoginLogMapper;

    /**
    * auther： lsd
    * create： 2021-07-28 23:27:14
    * describe： 分页获取登录信息
    * @param param   参数
    * @return
    */
    public OwnResult<OwnPageResult<PlatformLoginLogsDto>> getLoginLogs(PlatformLoginLogsParam param) throws Exception {
        IPage<PlatformLoginLogsDto> page = platformLoginLogMapper.getLoginLogs(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

}