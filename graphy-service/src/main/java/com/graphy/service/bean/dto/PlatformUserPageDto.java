package com.graphy.service.bean.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbPlatformUserEntity;

/**
 * @auther lsd
 * @create 2021-07-26 09:03:18
 * @describe 响应对象
 */
@Data
@ApiModel(value = "UserPageDto对象", description = "响应对象")
public class PlatformUserPageDto extends TbPlatformUserEntity {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    private String statusName;
    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String unitName;

}