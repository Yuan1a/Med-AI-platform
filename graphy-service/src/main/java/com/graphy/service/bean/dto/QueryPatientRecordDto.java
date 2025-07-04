package com.graphy.service.bean.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusPatientEntity;

/**
* @auther lsd
* @create 2022-03-01 15:59:46
* @describe 响应对象
*/
@Data
@ApiModel(value = "QueryPatientRecordDto对象", description = "响应对象")
public class QueryPatientRecordDto extends TbBusPatientEntity  {

    /**
     * 放射号
     */
    @ApiModelProperty(value = "放射号")
    private String radiationId;
    /**
     * 报告医生
     */
    @ApiModelProperty(value = "报告医生")
    private String reportDoctor;
    /**
     * 报告机构名称
     */
    @ApiModelProperty(value = "报告机构名称")
    private String reportUnitName;
}