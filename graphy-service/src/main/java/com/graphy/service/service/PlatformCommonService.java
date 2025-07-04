package com.graphy.service.service;

import com.graphy.db.entity.TbPlatformCodeEntity;
import com.graphy.db.entity.TbPlatformConfigEntity;
import com.graphy.service.bean.OwnUserInfo;
import com.graphy.unit.OwnValueText;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @auther lsd
 * @create 2021-08-01 00:44:39
 * @describe 公共使用 注意当前类不得有操作数据库得操作
 */
public interface PlatformCommonService {

    /**
     * 获取字典详细信息
     *
     * @param type
     * @param code
     * @return
     * @throws Exception
     */
    TbPlatformCodeEntity getCode(String type, String code) throws Exception;

    /**
     * 获取字典信息
     *
     * @param type 字典类别
     * @return
     * @throws Exception
     */
    List<OwnValueText> getCodes(String type) throws Exception;


    /**
     * 传输图片文件
     *
     * @param filePath 文件地址
     * @param response
     * @throws Exception
     */
    void imageLoad(String filePath, HttpServletResponse response) throws Exception;

    /**
     * 保存图片信息
     *
     * @param images
     * @return
     */
    String imageSave(String images) throws Exception;

    /**
     * 获取当前登录用户ID
     *
     * @return
     * @throws Exception
     */
    OwnUserInfo getUserInfo() throws Exception;

    /**
     * 设置平台高级配置进缓存
     *
     * @return
     */
    TbPlatformConfigEntity setPlatformConfigToCache(TbPlatformConfigEntity configEntity) throws Exception;

    /**
     * 获取平台配置
     *
     * @return
     */
    TbPlatformConfigEntity getPlatformConfig() throws Exception;
}