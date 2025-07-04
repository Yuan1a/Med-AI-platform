package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
* @auther lsd
* @create 2022-03-01 12:52:43
* @describe 请求参数
*/
@Data
@ApiModel(value = "DiagnoseRecordParam对象", description = "请求参数")
public class DiagnoseRecordParam extends OwnPageParam  {


    /**
    * 患者ID
    */
    @ApiModelProperty(value="患者ID",required = false)
    private String patientId;
    /**
     * 患者姓名
     */
    @ApiModelProperty(value="患者姓名",required = false)
    private String patientName;
    /**
    * 诊断病种
    */
    @ApiModelProperty(value="诊断病种",required = false)
    private String diagnosisDiseases;

    /**
    * 诊断医生
    */
    @ApiModelProperty(value="诊断医生",required = false)
    private String diagnosisDoctor;

    /**
    * 诊断机构
    */
    @ApiModelProperty(value="诊断机构",required = false)
    private String diagnosisUnit;

    /**
    * 科室编码 关联字典office类别
    */
    @ApiModelProperty(value="科室编码 关联字典office类别",required = false)
    private String officeCode;

    /**
    * 科室名称
    */
    @ApiModelProperty(value="科室名称",required = false)
    private String officeName;
    /**
     * 放射号
     */
    @ApiModelProperty(value = "放射号")
    private String radiationId;
}