package com.graphy.service.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.db.entity.TbPlatformCodeEntity;
import com.graphy.db.entity.TbPlatformConfigEntity;
import com.graphy.db.service.TbPlatformCodeService;
import com.graphy.db.service.TbPlatformConfigService;
import com.graphy.service.bean.OwnUserInfo;
import com.graphy.config.BaseConfig;
import com.graphy.unit.OwnValueText;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.service.PlatformCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @auther lsd
 * @create 2021-08-01 00:44:39
 * @describe 公共使用 注意当前类不得有操作数据库得操作
 */
@Service("com.graphy.service.service.impl.commonimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformCommonImpl implements PlatformCommonService {
    @Value("${server.servlet.context-path}")
    private String webLevel;
    @Value("${pro.file-folder}")
    private String fileFolder;

    private final TbPlatformCodeService tbPlatformCodeService;
    private final TbPlatformConfigService tbPlatformConfigService;

    /**
     * 获取字典详细信息
     *
     * @param type
     * @param code
     * @return
     * @throws Exception
     */
    public TbPlatformCodeEntity getCode(String type, String code) throws Exception {
        TbPlatformCodeEntity entity = tbPlatformCodeService
                .getOne(new LambdaQueryWrapper<TbPlatformCodeEntity>()
                        .eq(TbPlatformCodeEntity::getStatus, "1")
                        .eq(TbPlatformCodeEntity::getType, type)
                        .eq(TbPlatformCodeEntity::getCode, code));
        return entity;
    }

    /**
     * 获取字典信息
     *
     * @param type 字典类别
     * @return
     * @throws Exception
     */
    public List<OwnValueText> getCodes(String type) throws Exception {
        List<TbPlatformCodeEntity> list = tbPlatformCodeService.list(new LambdaQueryWrapper<TbPlatformCodeEntity>().eq(TbPlatformCodeEntity::getStatus, "1").eq(TbPlatformCodeEntity::getType, type).orderByDesc(TbPlatformCodeEntity::getOrd));
        List<OwnValueText> valueTexts = OwnValueText.list(list, "code", "name");
        return valueTexts;
    }


    /**
     * 传输图片文件
     *
     * @param filePath 文件地址
     * @param response
     * @throws Exception
     */
    public void imageLoad(String filePath, HttpServletResponse response) throws Exception {
        int lastIndexOf = filePath.lastIndexOf(".");
        String suffix = filePath.substring(lastIndexOf + 1);
        byte[] imageByte = com.graphy.unit.image.Api.getImageByteByPath(fileFolder + filePath);
        com.graphy.unit.http.Api.responseImage(response, imageByte, suffix);
    }

    /**
     * 保存图片信息
     *
     * @param images
     * @return
     */
    public String imageSave(String images) throws Exception {
        if (StrUtil.hasEmpty(images)) return images;
        StringBuilder sb = new StringBuilder();
        String[] _images = images.split("_");
        for (String item : _images) {
            if (item.contains("base64,")) {
                String newImage = com.graphy.unit.image.Api.base64SaveImage(item, fileFolder, null).getLocalPath();
                sb.append(sb.toString().equals("") ? newImage : "," + newImage);
            } else {
                sb.append(sb.toString().equals("") ? item : "," + item);
            }
        }
        return sb.toString();
    }

    /**
     * 设置平台高级配置进缓存
     *
     * @return
     */
    public TbPlatformConfigEntity setPlatformConfigToCache(TbPlatformConfigEntity configEntity) throws Exception {

        if (StrUtil.hasEmpty(configEntity.getPlatformLogoIco())) {
            configEntity.setPlatformLogoIco(webLevel+"/image/logo.ico");
        } else {
            configEntity.setPlatformLogoIco(webLevel+"/pf/PlatformImage/load" + configEntity.getPlatformLogoIco());
        }
        if (StrUtil.hasEmpty(configEntity.getPlatformLogoPng())) {
            configEntity.setPlatformLogoPng(webLevel+"/image/logo.png");
        } else {
            configEntity.setPlatformLogoPng(webLevel+"/pf/PlatformImage/load" + configEntity.getPlatformLogoPng());
        }
        if (StrUtil.hasEmpty(configEntity.getPlatformLoginBg())) {
            configEntity.setPlatformLoginBg(webLevel+"/image/logo-bg.png");
        } else {
            configEntity.setPlatformLoginBg(webLevel+"/pf/PlatformImage/load" + configEntity.getPlatformLoginBg());
        }
        com.graphy.unit.redis.Api.add(BaseConfig.PRO_KEY, BaseConfig.CACHE_PLATFORM_CONFIG_NAME, configEntity);
        return configEntity;
    }


    /**
     * 获取平台配置
     *
     * @return
     */
    public TbPlatformConfigEntity getPlatformConfig() throws Exception {
        TbPlatformConfigEntity configEntity = com.graphy.unit.redis.Api.get(BaseConfig.PRO_KEY, BaseConfig.CACHE_PLATFORM_CONFIG_NAME, TbPlatformConfigEntity.class);
        if (configEntity == null) {
            configEntity = tbPlatformConfigService.getOne(new LambdaQueryWrapper<TbPlatformConfigEntity>().eq(TbPlatformConfigEntity::getStatus, "1"));
            if (configEntity == null) {
                configEntity = new TbPlatformConfigEntity();
                configEntity.setPlatformName("后台管理系统");
                configEntity.setPlatformSafetyIdeCodeIsUse("1");
                configEntity.setPlatformSafetyIdeCodeOutTime(60);
                configEntity.setPlatformSafetyPasswordLevel(3);
                configEntity.setPlatformLoginColor("#c0407f");
                configEntity.setPlatformSafetyPasswordDefault("$OwnPass123$");
                configEntity.setPlatformSafetyPasswordMinLength(8);
                configEntity.setPlatformSafetyPasswordOutTime(365);
                configEntity.setPlatformSafetyTokenOutTime(60 * 60 * 8);
                configEntity.setPlatformSafetyTokenLevel(5);
                configEntity.setRequestLogKeepTime(30);
                configEntity.setRequestLogOpen("0");
                configEntity.setDbUpLogKeepTime(30);
                configEntity.setDbUpLogOpen("0");
                tbPlatformConfigService.save(configEntity);
                configEntity = tbPlatformConfigService.getOne(new LambdaQueryWrapper<TbPlatformConfigEntity>().eq(TbPlatformConfigEntity::getStatus, "1"));
            }
            configEntity = setPlatformConfigToCache(configEntity);
        }
        return configEntity;
    }


    /**
     * 获取当前登录用户ID
     * 注：此方法不得有任何更改数据库得操作,不然你会天打五雷轰
     * 注：此方法不得有任何更改数据库得操作,不然你会天打五雷轰
     * 注：此方法不得有任何更改数据库得操作,不然你会天打五雷轰
     * 注：此方法不得有任何更改数据库得操作,不然你会天打五雷轰
     * 注：此方法不得有任何更改数据库得操作,不然你会天打五雷轰
     * 注：此方法不得有任何更改数据库得操作,不然你会天打五雷轰
     *
     * @return
     * @throws Exception
     */
    public OwnUserInfo getUserInfo() throws Exception {
        OwnUserInfo userInfo = new OwnUserInfo();
        try {
            if (RequestContextHolder.getRequestAttributes() != null) {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                if (request != null) {
                    //平台端
                    String requestURI = request.getRequestURI();
                    if (requestURI.startsWith(webLevel+"/pf/") || requestURI.startsWith("/counter/")) {
                        String tokenName = BaseConfig.PRO_KEY + "_" + BaseConfig.TOKEN_PLATFORM;
                        String token = com.graphy.unit.http.Api.getCookie(request, BaseConfig.PRO_KEY + "_" + BaseConfig.TOKEN_PLATFORM);
                        if (StrUtil.hasEmpty(token)) {
                            token = request.getHeader(tokenName);
                        }
                        if (!StrUtil.hasEmpty(token)) {
                            String userTokenValue = com.graphy.unit.redis.Api.get(BaseConfig.PRO_KEY, BaseConfig.TOKEN_PLATFORM + ":" + token);
                            if (!StrUtil.hasEmpty(userTokenValue)) {
                                JSONObject user = JSONObject.parseObject(userTokenValue);
                                if (user != null) {
                                    if (user.get("userId") != null) {
                                        userInfo.setUserId(user.get("userId").toString());
                                        userInfo.setUserName(user.get("userName").toString());
                                        userInfo.setToken(token);
                                    }
                                }
                            }
                        }
                    } else if (requestURI.startsWith("/api/")) {
                        String token = request.getHeader("user-token");
                        if (!StrUtil.hasEmpty(token)) {
                            String userId = com.graphy.unit.redis.Api.get(BaseConfig.PRO_KEY, "user-token:" + token);
                            if (!StrUtil.hasEmpty(userId)) {
                                userInfo.setUserId(userId);
                                userInfo.setUserName("小程序");
                                userInfo.setToken(token);
                            }
                        }
                    }
                    userInfo.setRequestUrl(request.getRequestURL().toString());
                    userInfo.setIp(com.graphy.unit.http.Api.getIpAddr(request));
                }
            }
        } catch (Exception err) {

        }
        if (StrUtil.hasEmpty(userInfo.getUserId())) {
            userInfo.setUserId("未知");
            userInfo.setHasLogin(false);
        } else {
            userInfo.setHasLogin(true);
        }
        if (StrUtil.hasEmpty(userInfo.getUserName())) userInfo.setUserName("未知");
        if (StrUtil.hasEmpty(userInfo.getRequestUrl())) userInfo.setRequestUrl("未知");
        if (StrUtil.hasEmpty(userInfo.getIp())) userInfo.setIp("未知");
        return userInfo;
    }


}