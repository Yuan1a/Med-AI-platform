package com.graphy.service.bean.param;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @auther lsd
 * @create 2021-07-24 17:04:19
 * @describe 请求参数
 */
@Data
@ApiModel(value = "ModuleListParam对象", description = "请求参数")
public class PlatformModuleListParam {


    /**
     * 父模块ID
     */
    @ApiModelProperty(value = "父模块ID", required = false)
    private String pid;

    /**
     * 菜单显示
     */
    @ApiModelProperty(value = "菜单显示", required = false)
    private String showInMenu;

}