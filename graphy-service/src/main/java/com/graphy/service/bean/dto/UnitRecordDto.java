package com.graphy.service.bean.dto;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusUnitEntity;

/**
* @auther lsd
* @create 2022-03-01 11:48:53
* @describe 响应对象
*/
@Data
@ApiModel(value = "UnitRecordDto对象", description = "响应对象")
public class UnitRecordDto extends TbBusUnitEntity  {


    /**
    * 类别名称
    */
    @ApiModelProperty(value="类别名称")
    private String typeName;

    /**
    * 医院级别名称
    */
    @ApiModelProperty(value="医院级别名称")
    private String gradeName;

    /**
    * 省名称
    */
    @ApiModelProperty(value="省名称")
    private String provName;

    /**
    * 市名称
    */
    @ApiModelProperty(value="市名称")
    private String cityName;

    /**
    * 区/县名称
    */
    @ApiModelProperty(value="区/县名称")
    private String countyName;

    /**
    * 乡/镇名称
    */
    @ApiModelProperty(value="乡/镇名称")
    private String townName;

}