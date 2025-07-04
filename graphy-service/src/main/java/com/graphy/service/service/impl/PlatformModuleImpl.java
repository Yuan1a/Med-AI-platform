package com.graphy.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.service.bean.dto.PlatformModuleListDto;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformModuleListParam;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformModuleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbPlatformModuleMapper;
import com.graphy.db.entity.TbPlatformModuleEntity;
import com.graphy.service.service.PlatformModuleService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import cn.hutool.core.util.StrUtil;

/**
 * @auther lsd
 * @create 2021-07-22 00:48:24
 * @describe 页面导航
 */
@Service("com.graphy.service.service.impl.platformmoduleimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformModuleImpl extends ServiceImpl<TbPlatformModuleMapper, TbPlatformModuleEntity> implements PlatformModuleService {

    private final PlatformModuleMapper platformModuleMapper;

    /**
     * auther： lsd
     * create： 2021-07-24 17:04:19
     * describe： 获取导航列表数据
     *
     * @param param 参数
     * @return
     */
    public OwnResult<List<PlatformModuleListDto>> getModuleList(PlatformModuleListParam param) throws Exception {
        List<PlatformModuleListDto> list = platformModuleMapper.getModuleList(param);
        return OwnResult.result(list);
    }

    /**
     * auther： lsd
     * create： 2021-07-24 23:05:20
     * describe： 保存导航信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<Boolean> saveModule(TbPlatformModuleEntity param) throws Exception {
        if (StrUtil.hasEmpty(param.getPid())) return OwnResult.error("请设置父模块ID");
        if (StrUtil.hasEmpty(param.getName())) return OwnResult.error("请设置模块名称");
        if (StrUtil.hasEmpty(param.getController())) return OwnResult.error("请设置控制器");
        if (StrUtil.hasEmpty(param.getIndex())) return OwnResult.error("请设置首页链接");
        if (param.getOrd() == null) return OwnResult.error("请设置排序");
        if (StrUtil.hasEmpty(param.getShowInMenu())) return OwnResult.error("请设置菜单显示");
        if (StrUtil.hasEmpty(param.getIsNewPage())) return OwnResult.error("请设置新页面");
        if (StrUtil.hasEmpty(param.getIsUse())) return OwnResult.error("请设置启用");
        Boolean ok = null;
        /*此处编写您的逻辑代码*/
        if (StrUtil.hasEmpty(param.getId())) {
            param.setForbidDel("0");
            param.setOrd(0);
            ok = this.save(param);
        } else {
            ok = this.update(param, new LambdaQueryWrapper<TbPlatformModuleEntity>().eq(TbPlatformModuleEntity::getId, param.getId()));
        }
        return OwnResult.result(ok);
    }

}