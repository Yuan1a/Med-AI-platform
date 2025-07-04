package com.graphy.service.bean.dto;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusLicensingApplyEntity;

/**
* @auther qwt
* @create 2022-12-12 11:16:04
* @describe 响应对象
*/
@Data
@ApiModel(value = "m654642626959507456", description = "对象描述")
public class ApprovalNumberDto extends TbBusLicensingApplyEntity  {


    /**
    * 该机构的需要审批的授权申请数
    */
    @ApiModelProperty(value="该机构的需要审批的授权申请数")
    private Integer number;

}