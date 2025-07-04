package com.graphy.service.bean.dto;
import com.baomidou.mybatisplus.annotation.TableField;
import com.graphy.db.entity.TbBusDiagnoseEntity;
import com.graphy.db.entity.TbBusIllnessEntity;
import com.graphy.db.entity.TbBusPatientEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModel;

/**
* @auther qwt
* @create 2023-06-15 09:41:48
* @describe 响应对象
*/
@Data
@ApiModel(value = "m721660658625544192", description = "对象描述")
public class IllnessStatisticsDto extends TbBusIllnessEntity {

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer number;

    /**
     * 病种名称
     */
    @ApiModelProperty(value = "病种名称")
    private String illnessName;

    /**
     * 病种ID
     */
    @ApiModelProperty(value = "病种ID")
    private String illnessId;



}