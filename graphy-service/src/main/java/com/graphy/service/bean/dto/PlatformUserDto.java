package com.graphy.service.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * 系统当前登录用户对象
 */
@Data
public class PlatformUserDto {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", example = "")
    public String userId;
    /**
     * 用户账户
     */
    @ApiModelProperty(value = "用户账户", example = "")
    public String userAccount;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", example = "")
    public String userName;
    /**
     * 已更新密码0=否1=是
     */
    @ApiModelProperty(value = "已更新密码0=否1=是", example = "")
    public String userUpdatePassword;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", example = "")
    public String userRoleId;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", example = "")
    public String userRoleName;
    /**
     * 权限模块
     */
    @ApiModelProperty(value = "权限模块", example = "")
    public Set<String> userPowerModule;
    /**
     * 头像图片
     */
    @ApiModelProperty(value = "头像图片", example = "")
    public String pic;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", example = "")
    public String mobile;
}
