package com.graphy.service.bean.param;
import com.graphy.db.entity.TbBusPatientEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModel;

/**
* @auther qwt
* @create 2023-06-15 09:41:48
* @describe 请求参数
*/
@Data
@ApiModel(value = "m721660658524880896", description = "对象描述")
public class IllnessStatisticsParam  extends TbBusPatientEntity {

    /**
     * 病种ID
     */
    @ApiModelProperty(value = "病种ID")
    private String illnessId;
}