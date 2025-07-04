package com.graphy.service.bean.dto;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusPatientEntity;

/**
* @auther qwt
* @create 2022-12-06 16:12:18
* @describe 响应对象
*/
@Data
@ApiModel(value = "m652542847425183744", description = "对象描述")
public class PatientInfoDto extends TbBusPatientEntity  {


    /**
    * 诊断机构
    */
    @ApiModelProperty(value="诊断机构")
    private String diagnosisUnit;

    /**
    * 诊断时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="诊断时间")
    private Date diagnosisTime;

    /**
    * 患者ID
    */
    @ApiModelProperty(value="患者ID")
    private String patientId;

    /**
    * 放射号
    */
    @ApiModelProperty(value="放射号")
    private String radiationId;

    /**
    * 影像报告正文
    */
    @ApiModelProperty(value="影像报告正文")
    private String straightMatter;

    /**
    * 报告医生
    */
    @ApiModelProperty(value="报告医生")
    private String diagnosisDoctor;

}