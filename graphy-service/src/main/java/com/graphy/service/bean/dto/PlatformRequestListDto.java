package com.graphy.service.bean.dto;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;

/**
* @auther lsd
* @create 2021-08-15 18:29:03
* @describe 响应对象
*/
@Data
@ApiModel(value = "RequestListDto对象", description = "响应对象")
public class PlatformRequestListDto {


    /**
    * 主键ID
    */
    @ApiModelProperty(value="主键ID")
    private String id;

    /**
    * 来源IP
    */
    @ApiModelProperty(value="来源IP")
    private String ip;

    /**
    * 请求地址
    */
    @ApiModelProperty(value="请求地址")
    private String url;

    /**
    * 请求方式
    */
    @ApiModelProperty(value="请求方式")
    private String mode;

    /**
    * 类型
    */
    @ApiModelProperty(value="类型")
    private String contentType;

    /**
    * 请求数据parameterMap
    */
    @ApiModelProperty(value="请求数据parameterMap")
    private String parameterMap;

    /**
    * 请求数据postData
    */
    @ApiModelProperty(value="请求数据postData")
    private String postData;

    /**
    * 请求数据头
    */
    @ApiModelProperty(value="请求数据头")
    private String header;

    /**
    * 记录保留截止时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="记录保留截止时间")
    private Date recordTimeOut;

    /**
    * 状态
    */
    @ApiModelProperty(value="状态")
    private String status;

    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="创建时间")
    private Date ctime;

    /**
    * 创建人
    */
    @ApiModelProperty(value="创建人")
    private String cuser;

    /**
    * 创建人名称
    */
    @ApiModelProperty(value="创建人名称")
    private String cuserName;

    /**
    * 修改时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="修改时间")
    private Date utime;

    /**
    * 修改人
    */
    @ApiModelProperty(value="修改人")
    private String uuser;

    /**
    * 修改人名称
    */
    @ApiModelProperty(value="修改人名称")
    private String uuserName;

    /**
    * 删除时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="删除时间")
    private Date dtime;

    /**
    * 删除人
    */
    @ApiModelProperty(value="删除人")
    private String duser;

    /**
    * 删除人名称
    */
    @ApiModelProperty(value="删除人名称")
    private String duserName;

}