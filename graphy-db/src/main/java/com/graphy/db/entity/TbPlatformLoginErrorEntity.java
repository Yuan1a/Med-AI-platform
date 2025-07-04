package com.graphy.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @auther lsd
 * @create 2021-09-30 16:16:55
 * @describe 实体类
 */
@Data
@TableName("tb_platform_login_error")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TbPlatformLoginErrorEntity对象", description = "")
public class TbPlatformLoginErrorEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    @TableId(value = "ID_", type = IdType.UUID)
    private String id;

    /**
    * 会话ID
    */
    @ApiModelProperty(value = "会话ID")
    @TableField("SESSION_ID_")
    private String sessionId;

    /**
    * IP地址
    */
    @ApiModelProperty(value = "IP地址")
    @TableField("IP_")
    private String ip;

    /**
    * 用户名
    */
    @ApiModelProperty(value = "用户名")
    @TableField("ACCOUNT_")
    private String account;

    /**
    * 用户密码
    */
    @ApiModelProperty(value = "用户密码")
    @TableField("PASSWORD_")
    private String password;

    /**
    * 验证码
    */
    @ApiModelProperty(value = "验证码")
    @TableField("VERIFY_CODE_")
    private String verifyCode;

    /**
    * 错误提示
    */
    @ApiModelProperty(value = "错误提示")
    @TableField("ERROR_")
    private String error;

    /**
    * 发生时间
    */
    @ApiModelProperty(value = "发生时间",example="2020-02-01 18:01:01")
    @TableField("TIME_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;


}