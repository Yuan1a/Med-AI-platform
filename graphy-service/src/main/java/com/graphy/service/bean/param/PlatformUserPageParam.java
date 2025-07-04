package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
* @auther lsd
* @create 2021-07-26 09:03:18
* @describe 请求参数
*/
@Data
@ApiModel(value = "UserPageParam对象", description = "请求参数")
public class PlatformUserPageParam extends OwnPageParam  {


    /**
    * 主键
    */
    @ApiModelProperty(value="主键",required = false)
    private String id;

    /**
    * 所属机构ID
    */
    @ApiModelProperty(value="所属机构ID",required = false)
    private String unit;

    /**
    * 真实姓名
    */
    @ApiModelProperty(value="真实姓名",required = false)
    private String name;

    /**
    * 手机号码
    */
    @ApiModelProperty(value="手机号码",required = false)
    private String mobile;

    /**
    * 用户名
    */
    @ApiModelProperty(value="用户名",required = false)
    private String account;

    /**
    * 账户角色
    */
    @ApiModelProperty(value="账户角色",required = false)
    private String role;

    /**
    * 是否更新密码
    */
    @ApiModelProperty(value="是否更新密码",required = false)
    private String updatePassword;

    /**
    * 状态
    */
    @ApiModelProperty(value="状态",required = false)
    private String status;

    /**
    * 禁止删除
    */
    @ApiModelProperty(value="禁止删除",required = false)
    private String forbidDel;

}