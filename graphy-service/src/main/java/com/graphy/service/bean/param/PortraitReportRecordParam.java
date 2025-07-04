package com.graphy.service.bean.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.graphy.db.entity.TbBusPortraitReportEntity;
import com.graphy.service.bean.OwnPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* @auther lsd
* @create 2022-03-01 12:48:05
* @describe 响应对象
*/
@Data
@ApiModel(value = "PortraitReportRecordDto对象", description = "响应对象")
public class PortraitReportRecordParam extends OwnPageParam {


    /**
    * 放射号
    */
    @ApiModelProperty(value="放射号")
    private String id;
    /**
     * 患者ID
     */
    @ApiModelProperty(value="患者ID")
    private String patientId;
    /**
     * 诊断医生
     */
    @ApiModelProperty(value="诊断医生")
    private String reportDoctor;
    /**
     * 诊断机构
     */
    @ApiModelProperty(value="诊断机构")
    private String reportUnit;
    /**
     * 诊断时间-开始
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="诊断时间-开始",required = false)
    private Date reportTimeStart;

    /**
     * 诊断时间-截止
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="诊断时间-截止",required = false)
    private Date reportTimeEnd;


}