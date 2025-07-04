package com.graphy.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformCodeTypePageParam;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformCodeTypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbPlatformCodeTypeMapper;
import com.graphy.db.entity.TbPlatformCodeTypeEntity;
import com.graphy.service.service.PlatformCodeTypeService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;

/**
 * @auther lsd
 * @create 2021-07-22 00:48:03
 * @describe 字典类别
 */
@Service("com.graphy.service.service.impl.platformcodetypeimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformCodeTypeImpl extends ServiceImpl<TbPlatformCodeTypeMapper, TbPlatformCodeTypeEntity> implements PlatformCodeTypeService {

    private final PlatformCodeTypeMapper platformCodeTypeMapper;

    /**
     * auther： lsd
     * create： 2021-07-24 13:16:36
     * describe： 分页获取字典类别信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<OwnPageResult<TbPlatformCodeTypeEntity>> getCodeTypePage(PlatformCodeTypePageParam param) throws Exception {
        IPage<TbPlatformCodeTypeEntity> page = platformCodeTypeMapper.getCodeTypePage(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    /**
     * auther： lsd
     * create： 2021-07-24 13:23:52
     * describe： 保存字典类别
     *
     * @param param 参数
     * @return
     */
    public OwnResult<Boolean> saveCodeType(TbPlatformCodeTypeEntity param) throws Exception {
        if (StrUtil.hasEmpty(param.getCode())) return OwnResult.error("请设置编码");
        if (StrUtil.hasEmpty(param.getName())) return OwnResult.error("请设置名称");

        boolean ok = false;
        /*此处编写您的逻辑代码*/
        if (StrUtil.hasEmpty(param.getId())) {
            param.setOrd(0);
            ok = this.save(param);
        } else {
            ok = this.update(param, new LambdaQueryWrapper<TbPlatformCodeTypeEntity>().eq(TbPlatformCodeTypeEntity::getId, param.getId()));
        }
        return OwnResult.result(ok);
    }

}