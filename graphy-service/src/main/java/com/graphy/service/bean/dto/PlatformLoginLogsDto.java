package com.graphy.service.bean.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbPlatformLoginEntity;

/**
 * @auther lsd
 * @create 2021-07-28 23:27:14
 * @describe 响应对象
 */
@Data
@ApiModel(value = "LoginLogsDto对象", description = "响应对象")
public class PlatformLoginLogsDto extends TbPlatformLoginEntity {


    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userAccount;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String userName;

}