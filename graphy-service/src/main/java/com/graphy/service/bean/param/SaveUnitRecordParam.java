package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusUnitEntity;

/**
* @auther lsd
* @create 2022-03-01 11:50:52
* @describe 请求参数
*/
@Data
@ApiModel(value = "SaveUnitRecordParam对象", description = "请求参数")
public class SaveUnitRecordParam extends TbBusUnitEntity  {


}