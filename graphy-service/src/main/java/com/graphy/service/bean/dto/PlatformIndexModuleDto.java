package com.graphy.service.bean.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @auther lsd
 * @create 2021-07-27 08:34:30
 * @describe 响应对象
 */
@Data
@ApiModel(value = "IndexModuleDto对象", description = "响应对象")
public class PlatformIndexModuleDto {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    public String id;

    /**
     * 父模块ID
     */
    @ApiModelProperty(value = "父模块ID")
    public String pid;

    /**
     * 模块名称
     */
    @ApiModelProperty(value = "模块名称")
    public String moduleName;

    /**
     * 控制器
     */
    @ApiModelProperty(value = "控制器")
    public String moduleController;

    /**
     * 首页链接
     */
    @ApiModelProperty(value = "首页链接")
    public String moduleIndex;

    /**
     * 新页面
     */
    @ApiModelProperty(value = "新页面")
    public String moduleIsNewPage;

    /**
     * 菜单显示
     */
    @ApiModelProperty(value = "菜单显示")
    public String showInMenu;


    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    public String moduleIcon;
    /**
     * 子节点
     */
    @ApiModelProperty(value = "子节点")
    public List<PlatformIndexModuleDto> children;
}