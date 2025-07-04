package com.graphy.service.bean.param;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusLicensingApplyEntity;

/**
* @auther qwt
* @create 2022-12-08 09:31:12
* @describe 请求参数
*/
@Data
@ApiModel(value = "m653166682671415296", description = "对象描述")
public class SavaLicensingApplyParam extends TbBusLicensingApplyEntity  {
    /**
     * 放射号
     */
    @ApiModelProperty(value = "患者id")
    private String radiationId;

}