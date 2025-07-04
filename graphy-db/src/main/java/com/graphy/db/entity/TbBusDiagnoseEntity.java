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
 * @describe 业务-诊断信息实体类
 */
@Data
@TableName("tb_bus_diagnose")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TbBusDiagnoseEntity对象", description = "业务-诊断信息")
public class TbBusDiagnoseEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    @TableId(value = "ID_", type = IdType.UUID)
    private String id;

    /**
    * 患者ID
    */
    @ApiModelProperty(value = "患者ID")
    @TableField("PATIENT_ID_")
    private String patientId;

    /**
    * 诊断状态
    */
    @ApiModelProperty(value = "诊断状态")
    @TableField("DIAGNOSIS_STATUS_")
    private String diagnosisStatus;
    /**
     * 诊断描述
     */
    @ApiModelProperty(value = "诊断描述")
    @TableField("DIAGNOSIS_DESCRIBE_")
    private String diagnosisDescribe;
//    /**
//    * 诊断病种
//    */
//    @ApiModelProperty(value = "诊断病种")
//    @TableField("DIAGNOSIS_DISEASES_")
//    private String diagnosisDiseases;
    /**
    * 病种信息ID
    */
    @ApiModelProperty(value = "病种信息ID")
    @TableField("ILLNESS_ID_")
    private String illnessId;
    /**
    * 诊断医生
    */
    @ApiModelProperty(value = "诊断医生")
    @TableField("DIAGNOSIS_DOCTOR_")
    private String diagnosisDoctor;

    /**
     * 诊断机构
     */
    @ApiModelProperty(value = "诊断机构")
    @TableField("DIAGNOSIS_UNIT_")
    private String diagnosisUnit;

    /**
    * 科室编码 关联字典office类别
    */
    @ApiModelProperty(value = "科室编码 关联字典office类别")
    @TableField("OFFICE_CODE_")
    private String officeCode;

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