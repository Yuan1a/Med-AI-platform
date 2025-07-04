package com.graphy.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformSaveConfigParam;

import com.graphy.service.service.PlatformCommonService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformConfigMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbPlatformConfigMapper;
import com.graphy.db.entity.TbPlatformConfigEntity;
import com.graphy.service.service.PlatformConfigService;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.util.StrUtil;

/**
 * @auther lsd
 * @create 2021-08-06 22:56:24
 * @describe 高级配置
 */
@Service("com.graphy.service.service.impl.platformconfigimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformConfigImpl extends ServiceImpl<TbPlatformConfigMapper, TbPlatformConfigEntity> implements PlatformConfigService {

    private final PlatformConfigMapper platformConfigMapper;
    private final PlatformCommonService platformCommonService;

    /**
     * auther： lsd
     * create： 2021-08-06 23:11:10
     * describe： 保存配置信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<Boolean> savePlatformConfig(PlatformSaveConfigParam param) throws Exception {
        if (StrUtil.hasEmpty(param.getId())) return OwnResult.error("请设置主键ID");
        if (StrUtil.hasEmpty(param.getPlatformName())) return OwnResult.error("请设置名称");
        if (StrUtil.hasEmpty(param.getPlatformSafetyIdeCodeIsUse())) return OwnResult.error("请设置启用验证码 0=否 1=是");
        if (param.getPlatformSafetyIdeCodeOutTime() == null) return OwnResult.error("请设置验证码过期时间(单位:秒)");
        if (param.getPlatformSafetyPasswordLevel() == null) return OwnResult.error("请设置密码强度 1=只验证密码个数 2=包含数字,字母 3=包含数字,字母,特殊字符 4=包含数字,大写字母,小写字符,特殊字符");
        if (StrUtil.hasEmpty(param.getPlatformSafetyPasswordDefault())) return OwnResult.error("请设置默认密码");
        if (param.getPlatformSafetyPasswordMinLength() == null) return OwnResult.error("请设置密码最小个数");
        if (param.getPlatformSafetyPasswordOutTime() == null) return OwnResult.error("请设置密码过期时间(单位:天)");
        if (param.getPlatformSafetyTokenOutTime() == null) return OwnResult.error("请设置令牌静默过期时间(单位:秒)");
        if (param.getPlatformSafetyTokenLevel() == null) return OwnResult.error("请设置令牌安全等级");


        param.setPlatformLoginBg(platformCommonService.imageSave(param.getPlatformLoginBg()));
        param.setPlatformLogoIco(platformCommonService.imageSave(param.getPlatformLogoIco()));
        param.setPlatformLogoPng(platformCommonService.imageSave(param.getPlatformLogoPng()));



        String msg = com.graphy.unit.pwdcheck.Api.checkPassword(param.getPlatformSafetyPasswordDefault(),
                param.getPlatformSafetyPasswordLevel(),
                param.getPlatformSafetyPasswordMinLength());
        if (!StrUtil.hasEmpty(msg)) return OwnResult.error("默认密码强度不够," + msg);

        /*此处编写您的逻辑代码*/
        TbPlatformConfigEntity entity = JSONObject.parseObject(JSONObject.toJSONString(param), TbPlatformConfigEntity.class);
        Boolean ok = this.saveOrUpdate(entity, new LambdaQueryWrapper<TbPlatformConfigEntity>().eq(TbPlatformConfigEntity::getId, param.getId()));
        entity = this.getOne(new LambdaQueryWrapper<TbPlatformConfigEntity>().eq(TbPlatformConfigEntity::getId, param.getId()));
        platformCommonService.setPlatformConfigToCache(entity);
        return OwnResult.result(ok);
    }

}