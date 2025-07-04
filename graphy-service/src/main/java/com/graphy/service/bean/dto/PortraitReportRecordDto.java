package com.graphy.service.bean.dto;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusPortraitReportEntity;

/**
* @auther lsd
* @create 2022-03-01 12:48:05
* @describe 响应对象
*/
@Data
@ApiModel(value = "PortraitReportRecordDto对象", description = "响应对象")
public class PortraitReportRecordDto extends TbBusPortraitReportEntity  {


    /**
    * 患者姓名
    */
    @ApiModelProperty(value="患者姓名")
    private String patientName;

    /**
    * 患者性别
    */
    @ApiModelProperty(value="患者性别")
    private String patientGenderName;
    /**
     * 诊断机构名称
     */
    @ApiModelProperty(value="诊断机构名称")
    private String reportUnitName;
    /**
    * 患者年龄
    */
    @ApiModelProperty(value="患者年龄")
    private Integer patientAge;

    /**
     * 诊断机构
     */
    @ApiModelProperty(value = "诊断机构")
    @TableField("DIAGNOSIS_UNIT_")
    private String diagnosisUnit;

}