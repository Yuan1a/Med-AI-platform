package com.graphy.service.bean.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbPlatformModuleEntity;

/**
 * @auther lsd
 * @create 2021-07-25 14:45:48
 * @describe 响应对象
 */
@Data
@ApiModel(value = "RolePowerDto对象", description = "响应对象")
public class PlatformRolePowerDto extends TbPlatformModuleEntity {


    /**
     * 选中
     */
    @ApiModelProperty(value = "选中")
    private Boolean check;
    /**
     * 是否存在子节点
     */
    @ApiModelProperty(value = "是否存在子节点", example = "")
    public boolean hasChildren;

}