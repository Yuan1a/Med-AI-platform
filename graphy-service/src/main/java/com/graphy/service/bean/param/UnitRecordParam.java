package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
* @auther lsd
* @create 2022-03-01 11:48:53
* @describe 请求参数
*/
@Data
@ApiModel(value = "UnitRecordParam对象", description = "请求参数")
public class UnitRecordParam extends OwnPageParam  {


    /**
    * 主键
    */
    @ApiModelProperty(value="主键",required = false)
    private String id;

    /**
    * 编码
    */
    @ApiModelProperty(value="编码",required = false)
    private String code;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称",required = false)
    private String name;

    /**
    * 机构类型 关联字典unit-type类别
    */
    @ApiModelProperty(value="机构类型 关联字典unit-type类别",required = false)
    private String type;

    /**
    * 医院级别 关联字典unit-grade类别
    */
    @ApiModelProperty(value="医院级别 关联字典unit-grade类别",required = false)
    private String grade;

    /**
    * 长地址
    */
    @ApiModelProperty(value="长地址",required = false)
    private String address;

    /**
    * 联系人
    */
    @ApiModelProperty(value="联系人",required = false)
    private String linkman;

    /**
    * 联系手机
    */
    @ApiModelProperty(value="联系手机",required = false)
    private String mobile;

    /**
    * 固定电话
    */
    @ApiModelProperty(value="固定电话",required = false)
    private String phone;

}