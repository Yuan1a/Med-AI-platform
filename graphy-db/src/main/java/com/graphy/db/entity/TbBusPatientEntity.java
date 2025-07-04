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
@TableName("tb_bus_patient")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TbBusPatientEntity对象", description = "")
public class TbBusPatientEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    @TableId(value = "ID_", type = IdType.UUID)
    private String id;

    /**
    * 放射号
    */
    @ApiModelProperty(value = "放射号")
    @TableField("RADIATION_ID_")
    private String radiationId;

    /**
    * 性别 1=男 2=女
    */
    @ApiModelProperty(value = "性别 1=男 2=女")
    @TableField("GENDER_")
    private String gender;

    /**
    * 年龄
    */
    @ApiModelProperty(value = "年龄")
    @TableField("AGE_")
    private Integer age;


    /**
     * 病种信息ID
     */
    @ApiModelProperty(value = "病种信息ID")
    @TableField("ILLNESS_ID_")
    private String illnessId;

//    /**
//     * 发病器官
//     */
//    @ApiModelProperty(value = "发病器官")
//    @TableField("ONSET_ORGAN_")
//    private String onsetOrgan;


    /**
     * 交易ID(区块链存储接口返回的字段)
     */
    @ApiModelProperty(value = "交易ID(区块链存储接口返回的字段)")
    @TableField("TXID_")
    private String txid;

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
     * 诊断状态 0=未诊断，1=已诊断
     */
    @ApiModelProperty(value = "诊断状态 0=未诊断，1=已诊断")
    @TableField("DIAGNOSIS_STATUS_")
    private String diagnosisStatus;
    /**
     * 诊断描述
     */
    @ApiModelProperty(value = "诊断描述")
    @TableField("DIAGNOSIS_DESCRIBE_")
    private String diagnosisDescribe;
    /**
     * 诊断时间
     */
    @ApiModelProperty(value = "诊断时间",example="2020-02-01 18:01:01",hidden = true)
    @TableField("DIAGNOSIS_TIME_")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date diagnosisTime;


    /**
     *危急值 0=正常 1=紧急  2=加急
     */
    @ApiModelProperty(value = "危急值 0=正常 1=紧急  2=加急")
    @TableField("CRISIS_VALUE_")
    private String crisisValue;


    /**
    * 状态 0=删除 1=有效
    */
    @ApiModelProperty(value = "状态 0=删除 1=有效",hidden = true)
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