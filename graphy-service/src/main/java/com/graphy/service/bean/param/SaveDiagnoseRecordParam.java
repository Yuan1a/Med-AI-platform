package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusDiagnoseEntity;

/**
* @auther lsd
* @create 2022-03-01 12:53:36
* @describe 请求参数
*/
@Data
@ApiModel(value = "SaveDiagnoseRecordParam对象", description = "请求参数")
public class SaveDiagnoseRecordParam extends TbBusDiagnoseEntity  {


}