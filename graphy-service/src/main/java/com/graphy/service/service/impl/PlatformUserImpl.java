package com.graphy.service.service.impl;

import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.db.entity.TbPlatformConfigEntity;
import com.graphy.service.bean.dto.PlatformUserPageDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformUserPageParam;
import com.graphy.config.BaseConfig;
import com.graphy.service.service.PlatformCommonService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbPlatformUserMapper;
import com.graphy.db.entity.TbPlatformUserEntity;
import com.graphy.service.service.PlatformUserService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;

import java.util.Date;
import java.util.List;

/**
 * @auther lsd
 * @create 2021-07-22 00:49:32
 * @describe 用户管理
 */
@Service("com.graphy.service.service.impl.platformuserimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformUserImpl extends ServiceImpl<TbPlatformUserMapper, TbPlatformUserEntity> implements PlatformUserService {

    private final PlatformUserMapper platformUserMapper;
    private final PlatformCommonService platformCommonService;


    /**
     * auther： lsd
     * create： 2021-07-26 09:03:18
     * describe： 分页获取用户信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<OwnPageResult<PlatformUserPageDto>> getUserPage(PlatformUserPageParam param) throws Exception {
        IPage<PlatformUserPageDto> page = platformUserMapper.getUserPage(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    /**
     * auther： lsd
     * create： 2021-07-26 10:53:50
     * describe： 保存用户信息
     *
     * @param param 参数
     * @return
     */
    public OwnResult<Boolean> saveUser(TbPlatformUserEntity param) throws Exception {
        if (StrUtil.hasEmpty(param.getName())) return OwnResult.error("请设置真实姓名");
        if (StrUtil.hasEmpty(param.getAccount())) return OwnResult.error("请设置用户名");
        if (StrUtil.hasEmpty(param.getPassword())) return OwnResult.error("请设置密码");
        if (StrUtil.hasEmpty(param.getIdCard())) return OwnResult.error("请设置身份证号");
        if (!IdcardUtil.isValidCard(param.getIdCard())) return OwnResult.error("身份证号格式错误");

        if (StrUtil.hasEmpty(param.getGender())) return OwnResult.error("请设置性别");
        if (!StrUtil.hasEmpty(param.getMobile()) && !com.graphy.unit.verify.Api.mobile(param.getMobile())) {
            return OwnResult.error("请输入正确的手机号码");
        }
        TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
        //密码强度验证
        String msg = com.graphy.unit.pwdcheck.Api.checkPassword(param.getPassword(), configEntity.getPlatformSafetyPasswordLevel(), configEntity.getPlatformSafetyPasswordMinLength());
        if (msg != null) return OwnResult.error(msg);

        List<TbPlatformUserEntity> userEntityList = this.list(new LambdaQueryWrapper<TbPlatformUserEntity>()
                .eq(TbPlatformUserEntity::getStatus, "1")
                .eq(TbPlatformUserEntity::getAccount, param.getAccount())
        );
        if (userEntityList.size() > 0) {
            TbPlatformUserEntity uOrm = userEntityList.get(0);
            if ((StrUtil.hasEmpty(param.getId()) && uOrm != null) || (!StrUtil.hasEmpty(param.getId()) && uOrm != null && !uOrm.getId().equals(param.getId()))) {
                return OwnResult.error(String.format("\"%s\"账号已存在!请重新更换账号"));
            }
        }
        param.setPic(platformCommonService.imageSave(param.getPic()));
        boolean ok = false;
        param.setPassword(com.graphy.unit.crypt.Api.encrypt(param.getPassword(), BaseConfig.CRYPT_KEY));
        /*此处编写您的逻辑代码*/
        if (StrUtil.hasEmpty(param.getId())) {
            param.setIsUse("1");
            param.setForbidDel("0");
            param.setUpdatePassword("1");
            param.setExpirePassword(com.graphy.unit.date.Api.dateAddSecond(new Date(), 60 * 60 * 24 * 365));
            ok = this.save(param);
        } else {
            ok = this.update(param, new LambdaQueryWrapper<TbPlatformUserEntity>().eq(TbPlatformUserEntity::getId, param.getId()));
        }
        return OwnResult.result(ok);
    }

}