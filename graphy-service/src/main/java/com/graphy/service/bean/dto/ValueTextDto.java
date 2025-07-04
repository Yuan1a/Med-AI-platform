package com.graphy.service.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 键值对象
 */
@ApiModel(value = "键值对象", description = "键值对象")
@Data
public class ValueTextDto {
    @ApiModelProperty(value = "键")
    private String value;
    @ApiModelProperty(value = "值")
    private String text;
    @ApiModelProperty(value = "医保类型 1是职工 2是居民")
    private String graphyType;

}
