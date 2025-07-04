package com.graphy.db.service.impl;

import com.graphy.db.entity.TbPlatformTaskLogEntity;
import com.graphy.db.mapper.TbPlatformTaskLogMapper;
import com.graphy.db.service.TbPlatformTaskLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @auther lsd
 * @create 2021-09-30 16:16:55
 * @describe 系统-任务日志服务实现类
 */
@Service("com.graphy.db.service.impl.tbplatformtasklogimpl")
public class TbPlatformTaskLogImpl extends ServiceImpl<TbPlatformTaskLogMapper, TbPlatformTaskLogEntity> implements TbPlatformTaskLogService {

}
