package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
* @auther lsd
* @create 2021-07-24 13:16:35
* @describe 请求参数
*/
@Data
@ApiModel(value = "CodeTypePageParam对象", description = "请求参数")
public class PlatformCodeTypePageParam extends OwnPageParam  {


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

}