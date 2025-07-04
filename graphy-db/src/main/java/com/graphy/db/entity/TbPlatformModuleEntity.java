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
 * @describe 系统-模块信息实体类
 */
@Data
@TableName("tb_platform_module")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TbPlatformModuleEntity对象", description = "系统-模块信息")
public class TbPlatformModuleEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    @TableId(value = "ID_", type = IdType.UUID)
    private String id;

    /**
    * 父模块ID
    */
    @ApiModelProperty(value = "父模块ID")
    @TableField("PID_")
    private String pid;

    /**
    * 模块名称
    */
    @ApiModelProperty(value = "模块名称")
    @TableField("NAME_")
    private String name;

    /**
    * 控制器
    */
    @ApiModelProperty(value = "控制器")
    @TableField("CONTROLLER_")
    private String controller;

    /**
    * 首页链接
    */
    @ApiModelProperty(value = "首页链接")
    @TableField("INDEX_")
    private String index;

    /**
    * 图标
    */
    @ApiModelProperty(value = "图标")
    @TableField("ICON_")
    private String icon;

    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK_")
    private String remark;

    /**
    * 排序
    */
    @ApiModelProperty(value = "排序")
    @TableField("ORD_")
    private Integer ord;

    /**
    * 菜单显示
    */
    @ApiModelProperty(value = "菜单显示")
    @TableField("SHOW_IN_MENU_")
    private String showInMenu;

    /**
    * 新页面
    */
    @ApiModelProperty(value = "新页面")
    @TableField("IS_NEW_PAGE_")
    private String isNewPage;

    /**
    * 启用
    */
    @ApiModelProperty(value = "启用")
    @TableField("IS_USE_")
    private String isUse;

    /**
    * 禁止删除
    */
    @ApiModelProperty(value = "禁止删除")
    @TableField("FORBID_DEL_")
    private String forbidDel;

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