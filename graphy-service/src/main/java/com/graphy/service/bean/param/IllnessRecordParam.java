package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
* @auther lsd
* @create 2022-03-01 12:42:07
* @describe 请求参数
*/
@Data
@ApiModel(value = "IllnessRecordParam对象", description = "请求参数")
public class IllnessRecordParam extends OwnPageParam  {


    /**
    * 主键
    */
    @ApiModelProperty(value="主键",required = false)
    private String id;

    /**
    * 病种类别 关联字典illness_type类别
    */
    @ApiModelProperty(value="病种类别 关联字典illness_type类别",required = false)
    private String type;

    /**
    * ICD编码
    */
    @ApiModelProperty(value="ICD编码",required = false)
    private String icd;

    /**
    * 病种名称
    */
    @ApiModelProperty(value="病种名称",required = false)
    private String name;

}