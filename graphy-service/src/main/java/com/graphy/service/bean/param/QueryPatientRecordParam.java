package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
* @auther lsd
* @create 2022-03-01 15:59:46
* @describe 请求参数
*/
@Data
@ApiModel(value = "QueryPatientRecordParam对象", description = "请求参数")
public class QueryPatientRecordParam extends OwnPageParam  {
    /**
     * 放射号
     */
    @ApiModelProperty(value = "放射号")
    private String radiationId;
    /**
     * choosetype=1 为诊断选择病人  choosetype=2为申请授权
     */
    @ApiModelProperty(value = "choosetype=1 为诊断 choosetype=2为申请授权")
    private String choosetype;
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
     * 患者所属机构
     */
    @ApiModelProperty(value="患者所属机构",required = false)
    private String unit;

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

}