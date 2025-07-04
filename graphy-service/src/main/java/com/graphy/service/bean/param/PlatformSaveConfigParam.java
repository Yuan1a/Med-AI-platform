package com.graphy.service.bean.param;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbPlatformConfigEntity;

/**
 * @auther lsd
 * @create 2021-08-06 23:11:10
 * @describe 请求参数
 */
@Data
@ApiModel(value = "SavePlatformConfigParam对象", description = "请求参数")
public class PlatformSaveConfigParam extends TbPlatformConfigEntity {


}