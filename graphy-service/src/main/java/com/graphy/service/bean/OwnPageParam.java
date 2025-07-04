package com.graphy.service.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 分页参数
 */
@ApiModel(value = "分页参数", description = "")
public class OwnPageParam {

    public OwnPageParam() {
        if (this.getSize() == null) this.setSize(20L);
        if (this.getPage() == null) this.setPage(1L);
    }
    /**
     * 分页大小
     */
    @ApiModelProperty(value = "分页大小", position = 1001, example = "20")
    private Long size;
    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码", position = 1002, example = "1")
    private Long page;

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }


}
