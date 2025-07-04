package com.graphy.service.bean.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbPlatformCodeEntity;

/**
 * @auther lsd
 * @create 2021-07-23 20:28:05
 * @describe 响应对象
 */
@Data
@ApiModel(value = "CodePageDto对象", description = "响应对象")
public class PlatformCodePageDto extends TbPlatformCodeEntity {


    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String codeTypeName;

}