package com.graphy.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @auther lsd
 * @create 2021-09-30 16:16:55
 * @describe 系统-网络请求实体类
 */
@Data
@TableName("tb_platform_request")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TbPlatformRequestEntity对象", description = "系统-网络请求")
public class TbPlatformRequestEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID_", type = IdType.UUID)
    private String id;

    /**
    * 来源IP
    */
    @ApiModelProperty(value = "来源IP")
    @TableField("IP_")
    private String ip;

    /**
    * 请求地址
    */
    @ApiModelProperty(value = "请求地址")
    @TableField("URL_")
    private String url;

    /**
    * 请求方式
    */
    @ApiModelProperty(value = "请求方式")
    @TableField("MODE_")
    private String mode;

    /**
    * 类型
    */
    @ApiModelProperty(value = "类型")
    @TableField("CONTENT_TYPE_")
    private String contentType;

    /**
    * 请求数据parameterMap
    */
    @ApiModelProperty(value = "请求数据parameterMap")
    @TableField("PARAMETER_MAP_")
    private String parameterMap;

    /**
    * 请求数据postData
    */
    @ApiModelProperty(value = "请求数据postData")
    @TableField("POST_DATA_")
    private String postData;

    /**
    * 请求数据头
    */
    @ApiModelProperty(value = "请求数据头")
    @TableField("HEADER_")
    private String header;

    /**
    * 记录保留截止时间
    */
    @ApiModelProperty(value = "记录保留截止时间",example="2020-02-01 18:01:01")
    @TableField("RECORD_TIME_OUT_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordTimeOut;

    /**
    * 状态
    */
    @ApiModelProperty(value = "状态",hidden = true)
    @TableField("STATUS_")
    private String status;

    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间",example="2020-02-01 18:01:01",hidden = true)
    @TableField("CTIME_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人",hidden = true)
    @TableField("CUSER_")
    private String cuser;

    /**
    * 创建人名称
    */
    @ApiModelProperty(value = "创建人名称",hidden = true)
    @TableField("CUSER_NAME_")
    private String cuserName;

    /**
    * 修改时间
    */
    @ApiModelProperty(value = "修改时间",example="2020-02-01 18:01:01",hidden = true)
    @TableField("UTIME_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date utime;

    /**
    * 修改人
    */
    @ApiModelProperty(value = "修改人",hidden = true)
    @TableField("UUSER_")
    private String uuser;

    /**
    * 修改人名称
    */
    @ApiModelProperty(value = "修改人名称",hidden = true)
    @TableField("UUSER_NAME_")
    private String uuserName;

    /**
    * 删除时间
    */
    @ApiModelProperty(value = "删除时间",example="2020-02-01 18:01:01",hidden = true)
    @TableField("DTIME_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dtime;

    /**
    * 删除人
    */
    @ApiModelProperty(value = "删除人",hidden = true)
    @TableField("DUSER_")
    private String duser;

    /**
    * 删除人名称
    */
    @ApiModelProperty(value = "删除人名称",hidden = true)
    @TableField("DUSER_NAME_")
    private String duserName;


}