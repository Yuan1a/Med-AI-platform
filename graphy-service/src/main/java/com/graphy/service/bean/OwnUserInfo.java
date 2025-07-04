package com.graphy.service.bean;

import lombok.Data;

@Data
public class OwnUserInfo {
    /**
     * 是否登录
     */
    private Boolean hasLogin;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 请求地址
     */
    private String requestUrl;
    /**
     * 令牌
     */
    private String token;
    /**
     * 来源IP
     */
    private String ip;

}
