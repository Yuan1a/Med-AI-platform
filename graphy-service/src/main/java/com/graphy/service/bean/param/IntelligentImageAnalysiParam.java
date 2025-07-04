package com.graphy.service.bean.param;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModel;

import java.io.File;

/**
* @auther qwt
* @create 2023-11-15 11:56:00
* @describe 请求参数
*/
@Data
@ApiModel(value = "m777139774916919296", description = "对象描述")
public class IntelligentImageAnalysiParam   {

    /**
     * 影像图片
     */
    @ApiModelProperty(value = "影像图片")
    private String file;
}