package com.graphy.service.bean.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModel;

/**
 * @auther lsd
 * @create 2021-07-25 14:45:48
 * @describe 请求参数
 */
@Data
@ApiModel(value = "RolePowerParam对象", description = "请求参数")
public class PlatformRolePowerParam {

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", required = true, hidden = false, example = "")
    public String roleId;

}