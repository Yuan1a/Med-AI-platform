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
* @create 2021-07-29 21:30:08
* @describe 请求参数
*/
@Data
@ApiModel(value = "LoginErrorsParam对象", description = "请求参数")
public class PlatformLoginErrorsParam extends OwnPageParam  {


    /**
    * 主键
    */
    @ApiModelProperty(value="主键",required = false)
    private String id;

    /**
    * 会话ID
    */
    @ApiModelProperty(value="会话ID",required = false)
    private String sessionId;

    /**
    * IP地址
    */
    @ApiModelProperty(value="IP地址",required = false)
    private String ip;

    /**
    * 用户名
    */
    @ApiModelProperty(value="用户名",required = false)
    private String account;

    /**
    * 用户密码
    */
    @ApiModelProperty(value="用户密码",required = false)
    private String password;

    /**
    * 验证码
    */
    @ApiModelProperty(value="验证码",required = false)
    private String verifyCode;

    /**
    * 错误提示
    */
    @ApiModelProperty(value="错误提示",required = false)
    private String error;

    /**
    * 发生时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="发生时间-开始",required = false)
    private Date timeStart;

    /**
    * 发生时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="发生时间-截止",required = false)
    private Date timeEnd;

}