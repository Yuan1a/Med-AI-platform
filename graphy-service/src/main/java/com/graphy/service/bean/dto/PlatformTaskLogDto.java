package com.graphy.service.bean.dto;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;

/**
* @auther lsd
* @create 2021-08-09 22:40:25
* @describe 响应对象
*/
@Data
@ApiModel(value = "PlatformTaskLogDto对象", description = "响应对象")
public class PlatformTaskLogDto   {


    /**
    * 主键ID
    */
    @ApiModelProperty(value="主键ID")
    private String taskId;

    /**
    * 任务名称
    */
    @ApiModelProperty(value="任务名称")
    private String taskName;

    /**
    * 主键ID
    */
    @ApiModelProperty(value="主键ID")
    private String taskLogId;

    /**
    * 开始执行时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="开始执行时间")
    private Date runStartTime;

    /**
    * 结束执行时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="结束执行时间")
    private Date runEndTime;

    /**
    * 发生异常 1=是 0=否
    */
    @ApiModelProperty(value="发生异常 1=是 0=否")
    private String happenError;

    /**
    * 异常信息
    */
    @ApiModelProperty(value="异常信息")
    private String error;

    /**
    * 执行结果
    */
    @ApiModelProperty(value="执行结果")
    private String result;

}