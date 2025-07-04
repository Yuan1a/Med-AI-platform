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
 * @create 2022-03-01 11:20:04
 * @describe 实体类
 */
@Data
@TableName("tb_bus_illness")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TbBusIllnessEntity对象", description = "")
public class TbBusIllnessEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    @TableId(value = "ID_", type = IdType.UUID)
    private String id;

    /**
    * 病种类别 关联字典illness_type类别
    */
    @ApiModelProperty(value = "病种类别 关联字典illness_type类别")
    @TableField("TYPE_")
    private String type;

    /**
    * ICD编码
    */
    @ApiModelProperty(value = "ICD编码")
    @TableField("ICD_")
    private String icd;

    /**
    * 病种名称
    */
    @ApiModelProperty(value = "病种名称")
    @TableField("NAME_")
    private String name;
    /**
     * 是否启动 0=禁用 1=启用
     */
    @ApiModelProperty(value = "是否启动 0=禁用 1=启用")
    @TableField("IS_USE_")
    private String isUse;

    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK_")
    private String remark;

    /**
    * 状态 状态 0=删除 1=有效
    */
    @ApiModelProperty(value = "状态 状态 0=删除 1=有效",hidden = true)
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