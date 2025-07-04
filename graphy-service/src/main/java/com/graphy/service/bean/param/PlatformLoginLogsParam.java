package com.graphy.service.bean.param;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
 * @auther lsd
 * @create 2021-07-28 23:18:39
 * @describe 请求参数
 */
@Data
@ApiModel(value = "LoginLogsParam对象", description = "请求参数")
public class PlatformLoginLogsParam extends OwnPageParam {


    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = false)
    private String id;

    /**
     * 账户ID
     */
    @ApiModelProperty(value = "账户ID", required = false)
    private String userId;

    /**
     * 令牌ID
     */
    @ApiModelProperty(value = "令牌ID", required = false)
    private String token;

    /**
     * 会话ID
     */
    @ApiModelProperty(value = "会话ID", required = false)
    private String sessionId;

    /**
     * IP地址
     */
    @ApiModelProperty(value = "IP地址", required = false)
    private String ip;

    /**
     * 创建时间-开始
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间-开始", required = false)
    private Date ctimeStart;

    /**
     * 创建时间-截止
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间-截止", required = false)
    private Date ctimeEnd;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userAccount;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String userName;

}