package com.graphy.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformTaskPageParam;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformTaskMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbPlatformTaskMapper;
import com.graphy.db.entity.TbPlatformTaskEntity;
import com.graphy.service.service.PlatformTaskService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;

/**
 * @auther lsd
 * @create 2021-08-08 11:37:35
 * @describe 定时任务
 */
@Service("com.graphy.service.service.impl.platformtaskimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformTaskImpl extends ServiceImpl<TbPlatformTaskMapper, TbPlatformTaskEntity> implements PlatformTaskService {

    private final PlatformTaskMapper platformTaskMapper;

    /**
     * auther： lsd
     * create： 2021-08-08 11:40:46
     * describe： 分页获取任务记录
     *
     * @param param 参数
     * @return
     */
    public OwnResult<OwnPageResult<TbPlatformTaskEntity>> getPlatformTaskPage(PlatformTaskPageParam param) throws Exception {
        IPage<TbPlatformTaskEntity> page = platformTaskMapper.getPlatformTaskPage(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    /**
     * auther： lsd
     * create： 2021-08-08 11:43:19
     * describe： 保存定时任务
     *
     * @param param 参数
     * @return
     */
    public OwnResult<Boolean> savePlatformTask(TbPlatformTaskEntity param) throws Exception {
        if (StrUtil.hasEmpty(param.getTaskName())) return OwnResult.error("请设置任务名称");
        if (StrUtil.hasEmpty(param.getCron())) return OwnResult.error("请设置计划配置cron表达式");
        if (StrUtil.hasEmpty(param.getBeanName())) return OwnResult.error("请设置bean名称");
        if (StrUtil.hasEmpty(param.getFunName())) return OwnResult.error("请设置函数名称");
        if (param.getLogTimeOut() == null) return OwnResult.error("请设置任务日志保留天数");
        if (param.getIndex() == null) return OwnResult.error("请设置运行顺序");
        if (StrUtil.hasEmpty(param.getRunStatus())) return OwnResult.error("请设置运行状态 run=运行中 pause=暂停");

        /*此处编写您的逻辑代码*/
        boolean ok = false;
        /*此处编写您的逻辑代码*/
        if (StrUtil.hasEmpty(param.getId())) {
            ok = this.save(param);
        } else {
            ok = this.update(param, new LambdaQueryWrapper<TbPlatformTaskEntity>().eq(TbPlatformTaskEntity::getId, param.getId()));
        }
        return OwnResult.result(ok);
    }

}