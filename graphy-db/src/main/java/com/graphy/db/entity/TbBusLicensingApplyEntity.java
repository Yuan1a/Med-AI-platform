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
 * @auther qwt
 * @create 2022-12-08 08:35:09
 * @describe 业务-影像授权申请实体类
 */
@Data
@TableName("tb_bus_licensing_apply")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TbBusLicensingApplyEntity对象", description = "业务-影像授权申请")
public class TbBusLicensingApplyEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    @TableId(value = "ID_", type = IdType.UUID)
    private String id;

    /**
    * 患者id
    */
    @ApiModelProperty(value = "患者id")
    @TableField("PATIENT_ID_")
    private String patientId;

    /**
    * 申请机构
    */
    @ApiModelProperty(value = "申请机构")
    @TableField("APPLY_UNIT_")
    private String applyUnit;

    /**
     * 申请人
     */
    @ApiModelProperty(value = "申请人")
    @TableField("APPLY_USER_")
    private String applyUser;

    /**
    * 授权(审批)机构
    */
    @ApiModelProperty(value = "授权(审批)机构")
    @TableField("APPROVAL_UNIT_")
    private String approvalUnit;

    /**
    * 审批状态
    */
    @ApiModelProperty(value = "审批状态")
    @TableField("APPROVAL_STATUS_")
    private String approvalStatus;


    /**
     * 审批人
     */
    @ApiModelProperty(value = "审批人")
    @TableField("APPROVAL_USER_")
    private String approvalUser;

    /**
    * 审批时间
    */
    @ApiModelProperty(value = "审批时间",example="2020-02-01 18:01:01")
    @TableField("APPROVAL_TIME_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;


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