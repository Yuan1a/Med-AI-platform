package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
* @auther lsd
* @create 2021-08-08 11:40:45
* @describe 请求参数
*/
@Data
@ApiModel(value = "PlatformTaskPageParam对象", description = "请求参数")
public class PlatformTaskPageParam extends OwnPageParam  {


    /**
    * 主键ID
    */
    @ApiModelProperty(value="主键ID",required = false)
    private String id;

    /**
    * 任务名称
    */
    @ApiModelProperty(value="任务名称",required = false)
    private String taskName;

    /**
    * bean名称
    */
    @ApiModelProperty(value="bean名称",required = false)
    private String beanName;

    /**
    * 函数名称
    */
    @ApiModelProperty(value="函数名称",required = false)
    private String funName;

    /**
    * 运行状态 run=运行中 pause=暂停
    */
    @ApiModelProperty(value="运行状态 run=运行中 pause=暂停",required = false)
    private String runStatus;

}