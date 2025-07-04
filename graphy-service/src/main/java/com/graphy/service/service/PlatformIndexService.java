package com.graphy.service.service;

import com.graphy.db.entity.TbPlatformUserEntity;
import com.graphy.service.bean.dto.PlatformIndexModuleDto;
import com.graphy.service.bean.OwnResult;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @auther lsd
 * @create 2021-07-22 00:45:16
 * @describe 平台首页
 */
public interface PlatformIndexService {

    /**
     * auther： lsd
     * create： 2021-07-27 08:34:30
     * describe： 获取用户导航权限
     *
     * @param request 请求对象
     * @return
     */
    OwnResult<List<PlatformIndexModuleDto>> getIndexModule(HttpServletRequest request) throws Exception;

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
    OwnResult<Boolean> upPassword(String userId, String oldPassword, String password, String confirmPassword) throws Exception;

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
    OwnResult<Boolean> upPasswordDirect(String userId, String password, String confirmPassword) throws Exception;

    /**
     * auther： lsd
     * create： 2021-07-28 22:32:50
     * describe： 保存用户信息
     *
     * @param param 参数
     * @return
     */
    OwnResult<TbPlatformUserEntity> saveUserInfo(TbPlatformUserEntity param) throws Exception;
}