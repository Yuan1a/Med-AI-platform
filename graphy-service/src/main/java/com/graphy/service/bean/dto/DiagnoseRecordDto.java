package com.graphy.service.bean.dto;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusDiagnoseEntity;

/**
* @auther lsd
* @create 2022-03-01 12:52:43
* @describe 响应对象
*/
@Data
@ApiModel(value = "DiagnoseRecordDto对象", description = "响应对象")
public class DiagnoseRecordDto extends TbBusDiagnoseEntity  {


    /**
    * 科室名称
    */
    @ApiModelProperty(value="科室名称")
    private String officeName;

    /**
    * 患者性别
    */
    @ApiModelProperty(value="患者性别")
    private String patientGenderName;

    /**
    * 患者年龄
    */
    @ApiModelProperty(value="患者年龄")
    private Integer patientAge;
    /**
     * 诊断机构名称
     */
    @ApiModelProperty(value="诊断机构名称")
    private String diagnosisUnitName;
    /**
     * 诊断病种名称
     */
    @ApiModelProperty(value="诊断病种名称")
    private String illnessName;

    /**
     * 放射号
     */
    @ApiModelProperty(value="放射号")
    private String radiationId;

}