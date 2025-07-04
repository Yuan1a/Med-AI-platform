package com.graphy.service.service.impl;

import java.util.Date;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.graphy.db.entity.TbPlatformConfigEntity;
import com.graphy.service.bean.dto.PlatformIndexModuleDto;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.dto.PlatformUserDto;
import com.graphy.db.entity.TbPlatformUserEntity;
import com.graphy.db.mapper.TbPlatformUserMapper;
import com.graphy.config.BaseConfig;
import com.graphy.service.service.PlatformCommonService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformIndexMapper;
import com.graphy.service.service.PlatformIndexService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @auther lsd
 * @create 2021-07-22 00:45:16
 * @describe 平台首页
 */
@Service("com.graphy.service.service.impl.platformindeximpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformIndexImpl implements PlatformIndexService {


    private final PlatformIndexMapper platformIndexMapper;

    private final TbPlatformUserMapper tbPlatformUserMapper;

    private final PlatformCommonService platformCommonService;

    /**
     * auther： lsd
     * create： 2021-07-27 08:34:30
     * describe： 获取用户导航权限
     *
     * @param request 参数
     * @return
     */
    public OwnResult<List<PlatformIndexModuleDto>> getIndexModule(HttpServletRequest request) throws Exception {
        String token = com.graphy.unit.http.Api.getCookie(request, BaseConfig.PRO_KEY + "_" + BaseConfig.TOKEN_PLATFORM);
        if (StrUtil.hasEmpty(token)) return OwnResult.error("令牌不能为空");
        PlatformUserDto user = com.graphy.unit.redis.Api.get(BaseConfig.PRO_KEY, BaseConfig.TOKEN_PLATFORM + ":" + token, PlatformUserDto.class);
        if (user == null) return OwnResult.error("无效的令牌");
        List<PlatformIndexModuleDto> list = platformIndexMapper.getIndexModule(user.userId);
        for (PlatformIndexModuleDto item : list) item.setChildren(new ArrayList<>());
        List<PlatformIndexModuleDto> powerModule = com.graphy.unit.recurrence.Api.recurrence(list, "id", "pid", "children", "0");
        return OwnResult.result(powerModule);
    }

    /**
     * auther： lsd
     * create： 2021-07-27 08:34:30
     * describe： 修改密码
     *
     * @param userId          用户ID
     * @param oldPassword     原始密码
     * @param password        修改密码
     * @param confirmPassword 确认密码
     * @return
     */
    public OwnResult<Boolean> upPassword(String userId, String oldPassword, String password, String confirmPassword) throws Exception {
        if (StrUtil.hasEmpty(oldPassword)) return OwnResult.error("请输入原始密码");
        if (StrUtil.hasEmpty(password)) return OwnResult.error("请输入新密码");
        if (StrUtil.hasEmpty(confirmPassword)) return OwnResult.error("请输入确认密码");
        TbPlatformUserEntity userOrm = tbPlatformUserMapper.selectById(userId);
        if (!userOrm.getPassword().equals(com.graphy.unit.crypt.Api.encrypt(oldPassword, BaseConfig.CRYPT_KEY))) return OwnResult.error("原始密码错误");
        if (userOrm.getPassword().equals(com.graphy.unit.crypt.Api.encrypt(password, BaseConfig.CRYPT_KEY))) return OwnResult.error("新密码不能等于原始密码");
        TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
        String msg = com.graphy.unit.pwdcheck.Api.checkPassword(password, configEntity.getPlatformSafetyPasswordLevel(), configEntity.getPlatformSafetyPasswordMinLength());
        if (!StrUtil.hasEmpty(msg)) return OwnResult.error(msg);
        if (!password.equals(confirmPassword)) return OwnResult.error("新密码与确认密码不一致");


        userOrm.setPassword(com.graphy.unit.crypt.Api.encrypt(password, BaseConfig.CRYPT_KEY));
        userOrm.setUpdatePassword("1");
        userOrm.setExpirePassword(com.graphy.unit.date.Api.dateAddSecond(new Date(), 60 * 60 * 24 * configEntity.getPlatformSafetyPasswordOutTime()));
        tbPlatformUserMapper.update(userOrm, new LambdaQueryWrapper<TbPlatformUserEntity>().eq(TbPlatformUserEntity::getId, userId));
        return OwnResult.result(true);
    }

    /**
     * auther： lsd
     * create： 2021-07-27 08:34:30
     * describe： 无原始密码修改
     *
     * @param userId          用户ID
     * @param password        修改密码
     * @param confirmPassword 确认密码
     * @return
     */
    public OwnResult<Boolean> upPasswordDirect(String userId, String password, String confirmPassword) throws Exception {
        if (StrUtil.hasEmpty(password)) return OwnResult.error("请输入新密码");
        if (StrUtil.hasEmpty(confirmPassword)) return OwnResult.error("请输入确认密码");
        TbPlatformUserEntity userOrm = tbPlatformUserMapper.selectById(userId);
        if (userOrm.getPassword().equals(com.graphy.unit.crypt.Api.encrypt(password, BaseConfig.CRYPT_KEY))) return OwnResult.error("新密码不能等于原始密码");

        TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
        String msg = com.graphy.unit.pwdcheck.Api.checkPassword(password, configEntity.getPlatformSafetyPasswordLevel(), configEntity.getPlatformSafetyPasswordMinLength());
        if (!StrUtil.hasEmpty(msg)) return OwnResult.error(msg);
        if (!password.equals(confirmPassword)) return OwnResult.error("新密码与确认密码不一致");
        userOrm.setPassword(com.graphy.unit.crypt.Api.encrypt(password, BaseConfig.CRYPT_KEY));
        userOrm.setUpdatePassword("1");
        userOrm.setExpirePassword(com.graphy.unit.date.Api.dateAddSecond(new Date(), 60 * 60 * 24 * 365));
        tbPlatformUserMapper.update(userOrm, new LambdaQueryWrapper<TbPlatformUserEntity>().eq(TbPlatformUserEntity::getId, userId));
        return OwnResult.result(true);
    }

    /**
     * auther： lsd
     * create： 2021-07-28 22:32:50
     * describe： 保存用户信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<TbPlatformUserEntity> saveUserInfo(TbPlatformUserEntity param) throws Exception {
        if (StrUtil.hasEmpty(param.getName())) return OwnResult.error("请输入您的姓名");
        if (StrUtil.hasEmpty(param.getGender())) return OwnResult.error("请选择性别");
        if (!StrUtil.hasEmpty(param.getMobile()) && !com.graphy.unit.verify.Api.mobile(param.getMobile())) {
            return OwnResult.error("请输入正确的手机号码");
        }
        TbPlatformUserEntity entity = new TbPlatformUserEntity();
        param.setPic(platformCommonService.imageSave(param.getPic()));
        entity.setName(param.getName());
        entity.setMobile(param.getMobile());
        entity.setGender(param.getGender());
        entity.setPic(param.getPic());
        entity.setRemark(param.getRemark());
        entity.setId(param.getId());
        tbPlatformUserMapper.update(entity, new LambdaQueryWrapper<TbPlatformUserEntity>().eq(TbPlatformUserEntity::getId, entity.getId()));
        return OwnResult.result(entity);
    }

}