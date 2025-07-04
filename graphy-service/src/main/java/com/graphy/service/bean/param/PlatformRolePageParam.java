package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
* @auther lsd
* @create 2021-07-25 14:08:25
* @describe 请求参数
*/
@Data
@ApiModel(value = "RolePageParam对象", description = "请求参数")
public class PlatformRolePageParam extends OwnPageParam  {


    /**
    * 角色名称
    */
    @ApiModelProperty(value="角色名称",required = false)
    private String name;

}