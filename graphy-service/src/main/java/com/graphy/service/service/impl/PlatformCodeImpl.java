package com.graphy.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.service.bean.dto.PlatformCodePageDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformCodePageParam;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformCodeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbPlatformCodeMapper;
import com.graphy.db.entity.TbPlatformCodeEntity;
import com.graphy.service.service.PlatformCodeService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;

/**
 * @auther lsd
 * @create 2021-07-22 00:47:45
 * @describe 字典管理
 */
@Service("com.graphy.service.service.impl.platformcodeimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformCodeImpl extends ServiceImpl<TbPlatformCodeMapper, TbPlatformCodeEntity> implements PlatformCodeService {

    private final PlatformCodeMapper platformCodeMapper;





    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 分页获取字典信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<OwnPageResult<PlatformCodePageDto>> getCodePage(PlatformCodePageParam param) throws Exception {
        IPage<PlatformCodePageDto> page = platformCodeMapper.getCodePage(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    /**
     * auther： lsd
     * create： 2021-07-24 00:09:34
     * describe： 保存字典信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<Boolean> saveCode(TbPlatformCodeEntity param) throws Exception {
        if (StrUtil.hasEmpty(param.getType())) return OwnResult.error("请设置类别");
        if (StrUtil.hasEmpty(param.getCode())) return OwnResult.error("请设置编码");
        if (StrUtil.hasEmpty(param.getName())) return OwnResult.error("请设置名称");
        Boolean ok = false;
        /*此处编写您的逻辑代码*/
        if (StrUtil.hasEmpty(param.getId())) {
            param.setOrd(0);
            param.setForbidDel("0");
            ok = this.save(param);
        } else {
            ok = this.update(param, new LambdaQueryWrapper<TbPlatformCodeEntity>().eq(TbPlatformCodeEntity::getId, param.getId()));
        }
        return OwnResult.result(ok);
    }

}