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
* @create 2021-08-07 23:11:12
* @describe 请求参数
*/
@Data
@ApiModel(value = "PlatformRequestPageParam对象", description = "请求参数")
public class PlatformRequestPageParam extends OwnPageParam  {


    /**
    * 来源IP
    */
    @ApiModelProperty(value="来源IP",required = false)
    private String ip;

    /**
    * 请求地址
    */
    @ApiModelProperty(value="请求地址",required = false)
    private String url;

    /**
    * 请求方式
    */
    @ApiModelProperty(value="请求方式",required = false)
    private String mode;

    /**
    * 类型
    */
    @ApiModelProperty(value="类型",required = false)
    private String contentType;

    /**
    * 请求数据parameterMap
    */
    @ApiModelProperty(value="请求数据parameterMap",required = false)
    private String parameterMap;

    /**
    * 请求数据postData
    */
    @ApiModelProperty(value="请求数据postData",required = false)
    private String postData;

    /**
    * 请求数据头
    */
    @ApiModelProperty(value="请求数据头",required = false)
    private String header;

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