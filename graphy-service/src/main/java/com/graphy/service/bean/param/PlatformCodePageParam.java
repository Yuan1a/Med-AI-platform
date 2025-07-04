package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
* @auther lsd
* @create 2021-07-23 20:28:05
* @describe 请求参数
*/
@Data
@ApiModel(value = "CodePageParam对象", description = "请求参数")
public class PlatformCodePageParam extends OwnPageParam  {


    /**
    * 主键ID
    */
    @ApiModelProperty(value="主键ID",required = false)
    private String id;

    /**
    * 类别
    */
    @ApiModelProperty(value="类别",required = false)
    private String type;

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