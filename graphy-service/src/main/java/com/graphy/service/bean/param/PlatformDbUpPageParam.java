package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
* @auther lsd
* @create 2021-08-07 23:08:31
* @describe 请求参数
*/
@Data
@ApiModel(value = "PlatformDbUpPageParam对象", description = "请求参数")
public class PlatformDbUpPageParam extends OwnPageParam  {


    /**
    * 主键ID
    */
    @ApiModelProperty(value="主键ID",required = false)
    private String id;

    /**
    * 表名称
    */
    @ApiModelProperty(value="表名称",required = false)
    private String tableName;

    /**
    * 修改内容
    */
    @ApiModelProperty(value="修改内容",required = false)
    private String upData;

    /**
    * 请求地址
    */
    @ApiModelProperty(value="请求地址",required = false)
    private String url;

    /**
    * 来源IP地址
    */
    @ApiModelProperty(value="来源IP地址",required = false)
    private String ip;

    /**
    * 处理状态 1=添加 2=修改 3=逻辑删除 4=物理删除
    */
    @ApiModelProperty(value="处理状态 1=添加 2=修改 3=逻辑删除 4=物理删除",required = false)
    private String handleStatus;

    /**
    * 创建时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="创建时间-开始",required = false)
    private Date ctimeStart;

    /**
    * 创建时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="创建时间-截止",required = false)
    private Date ctimeEnd;

    /**
    * 创建人
    */
    @ApiModelProperty(value="创建人",required = false)
    private String cuser;

    /**
    * 创建人名称
    */
    @ApiModelProperty(value="创建人名称",required = false)
    private String cuserName;

}