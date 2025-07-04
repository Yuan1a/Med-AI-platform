package com.graphy.service.bean.dto;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusIllnessEntity;

/**
* @auther lsd
* @create 2022-03-01 12:42:07
* @describe 响应对象
*/
@Data
@ApiModel(value = "IllnessRecordDto对象", description = "响应对象")
public class IllnessRecordDto extends TbBusIllnessEntity  {


    /**
    * 病种类别
    */
    @ApiModelProperty(value="病种类别")
    private String typeName;

}