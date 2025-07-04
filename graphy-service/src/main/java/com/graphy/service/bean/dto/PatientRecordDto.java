package com.graphy.service.bean.dto;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusPatientEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* @auther lsd
* @create 2022-03-01 11:54:13
* @describe 响应对象
*/
@Data
@ApiModel(value = "PatientRecordDto对象", description = "响应对象")
public class PatientRecordDto extends TbBusPatientEntity  {

    /**
     * 患者ID
     */
    @ApiModelProperty(value = "患者ID")
    private String patientId;
    /**
     * 影像报告正文
     */
    @ApiModelProperty(value = "影像报告正文")
    private String straightMatter;


    /**
     * 报告医生
     */
    @ApiModelProperty(value = "报告医生")
    private String reportDoctor;
    /**
     * 报告机构
     */
    @ApiModelProperty(value = "报告机构")
    private String reportUnit;
//    /**
//     * 报告机构名称
//     */
//    @ApiModelProperty(value = "报告机构名称")
//    private String reportUnitName;
    /**
     * 性别名称
     */
    @ApiModelProperty(value = "性别名称")
    private String genderName;
    /**
     * 危急值名称
     */
    @ApiModelProperty(value = "危急值名称")
    private String crisisValueName;
    /**
     * 诊断状态名称
     */
    @ApiModelProperty(value = "诊断状态名称")
    private String diagnosisStatusName;
    /**
     * 报告时间
     */
    @ApiModelProperty(value = "报告时间",example="2020-02-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reportTime;

    /**
     * 诊断描述
     */
    @ApiModelProperty(value = "诊断描述")
    private String diagnosisDescribe;
    /**
     * 诊断病种名称
     */
    @ApiModelProperty(value = "诊断病种名称")
    private String illnessName;
    /**
     * 病种ID
     */
    @ApiModelProperty(value = "病种ID")
    private String illnessId;


    /**
     * 诊断医生
     */
    @ApiModelProperty(value = "诊断医生")
    private String diagnosisDoctor;

    /**
     * 诊断机构
     */
    @ApiModelProperty(value = "诊断机构")
    private String diagnosisUnit;

    /**
     * 诊断机构名称
     */
    @ApiModelProperty(value = "诊断机构名称")
    private String diagnoseUnitName;

    /**
     * 诊断时间
     */
    @ApiModelProperty(value = "诊断时间",example="2020-02-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date diagnoseTime;
}