package com.graphy.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @auther lsd
 * @create 2021-11-15 15:06:18
 * @describe 系统-账户信息实体类
 */
@Data
@TableName("tb_platform_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TbPlatformUserEntity对象", description = "系统-账户信息")
public class TbPlatformUserEntity implements Serializable{

    private static final long serialVersionUID=1L;
    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    @TableId(value = "ID_", type = IdType.UUID)
    private String id;
    /**
     * 所属机构 关联机构信息tb_bus_unit表id_
     */
    @ApiModelProperty(value = "所属机构 关联tb_bus_unit表id_")
    @TableField("UNIT_")
    private String unit;
    /**
     * 用户类型 关联tb_platform_code表user-type 逗号隔开
     */
    @ApiModelProperty(value = "用户类型 关联tb_platform_code表user-type 逗号隔开")
    @TableField("USER_TYPE_")
    private String userType;
    /**
    * 真实姓名
    */
    @ApiModelProperty(value = "真实姓名")
    @TableField("NAME_")
    private String name;

    /**
    * 性别 1=男 2=女
    */
    @ApiModelProperty(value = "性别 1=男 2=女")
    @TableField("GENDER_")
    private String gender;

    /**
    * 手机号码
    */
    @ApiModelProperty(value = "手机号码")
    @TableField("MOBILE_")
    private String mobile;

    /**
    * 用户头像
    */
    @ApiModelProperty(value = "用户头像")
    @TableField("PIC_")
    private String pic;

    /**
    * 用户名
    */
    @ApiModelProperty(value = "用户名")
    @TableField("ACCOUNT_")
    private String account;

    /**
    * 密码
    */
    @ApiModelProperty(value = "密码")
    @TableField("PASSWORD_")
    private String password;

    /**
    * 账户角色
    */
    @ApiModelProperty(value = "账户角色")
    @TableField("ROLE_")
    private String role;

    /**
    * 是否更新密码
    */
    @ApiModelProperty(value = "是否更新密码")
    @TableField("UPDATE_PASSWORD_")
    private String updatePassword;

    /**
    * 密码过期时间
    */
    @ApiModelProperty(value = "密码过期时间",example="2020-02-01 18:01:01")
    @TableField("EXPIRE_PASSWORD_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirePassword;

    /**
    * 身份证号码
    */
    @ApiModelProperty(value = "身份证号码")
    @TableField("ID_CARD_")
    private String idCard;

    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK_")
    private String remark;

    /**
    * 启用 1=是 0=否
    */
    @ApiModelProperty(value = "启用 1=是 0=否")
    @TableField("IS_USE_")
    private String isUse;

    /**
    * 状态
    */
    @ApiModelProperty(value = "状态",hidden = true)
    @TableField("STATUS_")
    private String status;

    /**
    * 禁止删除
    */
    @ApiModelProperty(value = "禁止删除")
    @TableField("FORBID_DEL_")
    private String forbidDel;

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