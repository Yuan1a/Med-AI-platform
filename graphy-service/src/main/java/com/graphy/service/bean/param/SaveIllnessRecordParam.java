package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusIllnessEntity;

/**
* @auther lsd
* @create 2022-03-01 12:43:00
* @describe 请求参数
*/
@Data
@ApiModel(value = "SaveIllnessRecordParam对象", description = "请求参数")
public class SaveIllnessRecordParam extends TbBusIllnessEntity  {


}