package com.graphy.service.service.impl;
import com.graphy.service.bean.dto.PlatformTaskLogDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.param.PlatformTaskLogParam;

import com.graphy.service.bean.OwnResult;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformTaskLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbPlatformTaskLogMapper;
import com.graphy.db.entity.TbPlatformTaskLogEntity;
import com.graphy.service.service.PlatformTaskLogService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @auther lsd
 * @create 2021-08-08 23:26:38
 * @describe 任务日志
 */
@Service("com.graphy.service.service.impl.platformtasklogimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformTaskLogImpl extends ServiceImpl<TbPlatformTaskLogMapper, TbPlatformTaskLogEntity> implements PlatformTaskLogService {

    private final PlatformTaskLogMapper platformTaskLogMapper;

    /**
     * 清除过期数据
     *
     * @throws Exception
     */
    public OwnResult<Boolean> cleanData() throws Exception {
        try {
            platformTaskLogMapper.cleanData();
            return OwnResult.result(true);
        } catch (Exception err) {
            return OwnResult.error(err.toString());
        }
    }

    /**
    * auther： lsd
    * create： 2021-08-09 22:40:25
    * describe： 分页获取任务执行日志
    * @param param   参数
    * @return
    */
    public OwnResult<OwnPageResult<PlatformTaskLogDto>> getPlatformTaskLog(PlatformTaskLogParam param) throws Exception {
        IPage<PlatformTaskLogDto> page = platformTaskLogMapper.getPlatformTaskLog(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

}