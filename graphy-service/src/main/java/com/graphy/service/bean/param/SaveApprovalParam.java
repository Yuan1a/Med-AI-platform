package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusLicensingApplyEntity;

/**
* @auther qwt
* @create 2022-12-08 14:37:38
* @describe 请求参数
*/
@Data
@ApiModel(value = "m653243798775660544", description = "对象描述")
public class SaveApprovalParam extends TbBusLicensingApplyEntity  {


}