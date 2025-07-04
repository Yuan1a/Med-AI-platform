package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
* @auther lsd
* @create 2021-07-25 22:59:44
* @describe 请求参数
*/
@Data
@ApiModel(value = "UnitPageParam对象", description = "请求参数")
public class PlatformUnitPageParam extends OwnPageParam  {


    /**
    * 机构编码
    */
    @ApiModelProperty(value="机构编码",required = false)
    private String code;

    /**
    * 机构名称
    */
    @ApiModelProperty(value="机构名称",required = false)
    private String name;

}