package com.graphy.service.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
* 分页结果
*
* @param <T>
*/
@Data
@ApiModel(value = "分页结果", description = "")
@AllArgsConstructor
public class OwnPageResult<T> {
    /**
    * 总记录数
    */
    @ApiModelProperty(value = "总记录数", position = 1001)
    private Long total;
    /**
    * 分页大小
    */
    @ApiModelProperty(value = "分页大小", position = 1002)
    private Long size;
    /**
    * 当前页码
    */
    @ApiModelProperty(value = "当前页码", position = 1003)
    private Long page;
    /**
    * 记录集合
    */
    @ApiModelProperty(value = "记录集合", position = 1004)
    private List<T> records;

}