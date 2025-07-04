package com.graphy.service.bean.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbPlatformModuleEntity;

/**
 * @auther lsd
 * @create 2021-07-24 17:04:19
 * @describe 响应对象
 */
@Data
@ApiModel(value = "ModuleListDto对象", description = "响应对象")
public class PlatformModuleListDto extends TbPlatformModuleEntity {
    /**
     * 选中
     */
    @ApiModelProperty(value = "选中")
    private Boolean check;

    /**
     * 存在子节点
     */
    @ApiModelProperty(value = "存在子节点")
    private Boolean hasChildren;

}