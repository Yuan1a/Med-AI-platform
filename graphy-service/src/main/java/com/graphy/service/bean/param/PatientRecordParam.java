package com.graphy.service.bean.param;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* @auther lsd
* @create 2022-03-01 11:54:13
* @describe 请求参数
*/
@Data
@ApiModel(value = "PatientRecordParam对象", description = "请求参数")
public class PatientRecordParam extends OwnPageParam  {

    /**
     * 危急值
     */
    @ApiModelProperty(value="危急值",required = false)
    private String crisisValue;

    /**
     * 诊断状态
     */
    @ApiModelProperty(value="诊断状态",required = false)
    private String diagnosisStatus;
    /**
    * 主键
    */
    @ApiModelProperty(value="主键",required = false)
    private String id;

    /**
    * 姓名
    */
    @ApiModelProperty(value="姓名",required = false)
    private String name;

    /**
    * 1=男 2=女
    */
    @ApiModelProperty(value="1=男 2=女",required = false)
    private String gender;

    /**
    * 年龄-开始
    */
    @ApiModelProperty(value="年龄-开始",required = false)
    private Integer ageStart;

    /**
    * 年龄-截止
    */
    @ApiModelProperty(value="年龄-截止",required = false)
    private Integer ageEnd;
    /**
     * 诊断时间-开始
     */
    @ApiModelProperty(value = "诊断时间-开始",example="2020-02-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date diagnosisTimeStart;
    /**
     * 诊断时间-截至
     */
    @ApiModelProperty(value = "诊断时间-截至",example="2020-02-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date diagnosisTimeEnd;
    /**
     * 放射号
     */
    @ApiModelProperty(value = "放射号")
    private String radiationId;
    /**
     * 诊断机构
     */
    @ApiModelProperty(value = "诊断机构")
    private String diagnosisUnit;
    /**
     * 诊断医生
     */
    @ApiModelProperty(value = "诊断医生")
    private String diagnosisDoctor;

    /**
     * 病种ID
     */
    @ApiModelProperty(value = "病种ID")
    private String  illnessId;

}