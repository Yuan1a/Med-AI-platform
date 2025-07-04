package com.graphy.service.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* @auther 张小昌
* @create 2021-12-16 10:30:42
* @describe 请求参数
*/
@Data
@ApiModel(value = "TimeRangeCountParam对象", description = "请求参数")
public class TimeRangeCountParam   {


    /**
    * 统计时间
    */
    @ApiModelProperty(value="统计时间",required = false)
    private String applyTime;
    /**
     * 病种ID
     */
    @ApiModelProperty(value="病种ID",required = false)
    private String illnessId;
    /**
     * 定点医院
     */
    @ApiModelProperty(value="定点医院",required = false)
    private String unitFixed;
    /**
     * 定点区划编码
     */
    @ApiModelProperty(value="定点区划编码",required = false)
    private String unitCounty;
}