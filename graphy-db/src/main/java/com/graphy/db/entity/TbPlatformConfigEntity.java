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
 * @describe 系统-高级配置实体类
 */
@Data
@TableName("tb_platform_config")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TbPlatformConfigEntity对象", description = "系统-高级配置")
public class TbPlatformConfigEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID_", type = IdType.UUID)
    private String id;

    /**
    * 平台-名称
    */
    @ApiModelProperty(value = "平台-名称")
    @TableField("PLATFORM_NAME_")
    private String platformName;

    /**
    * 平台-启用验证码 0=否 1=是
    */
    @ApiModelProperty(value = "平台-启用验证码 0=否 1=是")
    @TableField("PLATFORM_SAFETY_IDE_CODE_IS_USE_")
    private String platformSafetyIdeCodeIsUse;

    /**
    * 平台-验证码过期时间(单位:秒)
    */
    @ApiModelProperty(value = "平台-验证码过期时间(单位:秒)")
    @TableField("PLATFORM_SAFETY_IDE_CODE_OUT_TIME_")
    private Integer platformSafetyIdeCodeOutTime;

    /**
    * 平台-密码强度 1=只验证密码个数 2=包含数字,字母 3=包含数字,字母,特殊字符 4=包含数字,大写字母,小写字符,特殊字符
    */
    @ApiModelProperty(value = "平台-密码强度 1=只验证密码个数 2=包含数字,字母 3=包含数字,字母,特殊字符 4=包含数字,大写字母,小写字符,特殊字符")
    @TableField("PLATFORM_SAFETY_PASSWORD_LEVEL_")
    private Integer platformSafetyPasswordLevel;

    /**
    * 平台-默认密码
    */
    @ApiModelProperty(value = "平台-默认密码")
    @TableField("PLATFORM_SAFETY_PASSWORD_DEFAULT_")
    private String platformSafetyPasswordDefault;

    /**
    * 平台-密码最小个数
    */
    @ApiModelProperty(value = "平台-密码最小个数")
    @TableField("PLATFORM_SAFETY_PASSWORD_MIN_LENGTH_")
    private Integer platformSafetyPasswordMinLength;

    /**
    * 平台-密码过期时间(单位:天)
    */
    @ApiModelProperty(value = "平台-密码过期时间(单位:天)")
    @TableField("PLATFORM_SAFETY_PASSWORD_OUT_TIME_")
    private Integer platformSafetyPasswordOutTime;

    /**
    * 平台-令牌静默过期时间(单位:秒)
    */
    @ApiModelProperty(value = "平台-令牌静默过期时间(单位:秒)")
    @TableField("PLATFORM_SAFETY_TOKEN_OUT_TIME_")
    private Integer platformSafetyTokenOutTime;

    /**
    * 平台-令牌安全等级
    */
    @ApiModelProperty(value = "平台-令牌安全等级")
    @TableField("PLATFORM_SAFETY_TOKEN_LEVEL_")
    private Integer platformSafetyTokenLevel;

    /**
    * 平台-logo图标
    */
    @ApiModelProperty(value = "平台-logo图标")
    @TableField("PLATFORM_LOGO_PNG_")
    private String platformLogoPng;

    /**
    * 平台-浏览器logo图标
    */
    @ApiModelProperty(value = "平台-浏览器logo图标")
    @TableField("PLATFORM_LOGO_ICO_")
    private String platformLogoIco;

    /**
    * 平台-登录页面背景图片
    */
    @ApiModelProperty(value = "平台-登录页面背景图片")
    @TableField("PLATFORM_LOGIN_BG_")
    private String platformLoginBg;

    /**
    * 平台-登录页面颜色
    */
    @ApiModelProperty(value = "平台-登录页面颜色")
    @TableField("PLATFORM_LOGIN_COLOR_")
    private String platformLoginColor;

    /**
    * 时时网络请求日志开启 1=是 0=否
    */
    @ApiModelProperty(value = "时时网络请求日志开启 1=是 0=否")
    @TableField("REQUEST_LOG_OPEN_")
    private String requestLogOpen;

    /**
    * 时时网络请求日志存档时间 单位：天
    */
    @ApiModelProperty(value = "时时网络请求日志存档时间 单位：天")
    @TableField("REQUEST_LOG_KEEP_TIME_")
    private Integer requestLogKeepTime;

    /**
    * 数据库日志记录开启 1=是 0=否
    */
    @ApiModelProperty(value = "数据库日志记录开启 1=是 0=否")
    @TableField("DB_UP_LOG_OPEN_")
    private String dbUpLogOpen;

    /**
    * 数据库日志记录存档时间 单位：天
    */
    @ApiModelProperty(value = "数据库日志记录存档时间 单位：天")
    @TableField("DB_UP_LOG_KEEP_TIME_")
    private Integer dbUpLogKeepTime;

    /**
    * 状态
    */
    @ApiModelProperty(value = "状态",hidden = true)
    @TableField("STATUS_")
    private String status;

    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间",example="2020-02-01 18:01:01",hidden = true)
    @TableField("CTIME_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人",hidden = true)
    @TableField("CUSER_")
    private String cuser;

    /**
    * 创建人名称
    */
    @ApiModelProperty(value = "创建人名称",hidden = true)
    @TableField("CUSER_NAME_")
    private String cuserName;

    /**
    * 修改时间
    */
    @ApiModelProperty(value = "修改时间",example="2020-02-01 18:01:01",hidden = true)
    @TableField("UTIME_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date utime;

    /**
    * 修改人
    */
    @ApiModelProperty(value = "修改人",hidden = true)
    @TableField("UUSER_")
    private String uuser;

    /**
    * 修改人名称
    */
    @ApiModelProperty(value = "修改人名称",hidden = true)
    @TableField("UUSER_NAME_")
    private String uuserName;

    /**
    * 删除时间
    */
    @ApiModelProperty(value = "删除时间",example="2020-02-01 18:01:01",hidden = true)
    @TableField("DTIME_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dtime;

    /**
    * 删除人
    */
    @ApiModelProperty(value = "删除人",hidden = true)
    @TableField("DUSER_")
    private String duser;

    /**
    * 删除人名称
    */
    @ApiModelProperty(value = "删除人名称",hidden = true)
    @TableField("DUSER_NAME_")
    private String duserName;


}