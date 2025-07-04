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
* @create 2021-08-09 22:40:25
* @describe 请求参数
*/
@Data
@ApiModel(value = "PlatformTaskLogParam对象", description = "请求参数")
public class PlatformTaskLogParam extends OwnPageParam  {


    /**
    * 主键ID
    */
    @ApiModelProperty(value="主键ID",required = false)
    private String taskId;

    /**
    * 任务名称
    */
    @ApiModelProperty(value="任务名称",required = false)
    private String taskName;

    /**
    * 主键ID
    */
    @ApiModelProperty(value="主键ID",required = false)
    private String taskLogId;

    /**
    * 开始执行时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="开始执行时间-开始",required = false)
    private Date runStartTimeStart;

    /**
    * 开始执行时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="开始执行时间-截止",required = false)
    private Date runStartTimeEnd;

    /**
    * 结束执行时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="结束执行时间-开始",required = false)
    private Date runEndTimeStart;

    /**
    * 结束执行时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="结束执行时间-截止",required = false)
    private Date runEndTimeEnd;

    /**
    * 发生异常 1=是 0=否
    */
    @ApiModelProperty(value="发生异常 1=是 0=否",required = false)
    private String happenError;

    /**
    * 异常信息
    */
    @ApiModelProperty(value="异常信息",required = false)
    private String error;

    /**
    * 执行结果
    */
    @ApiModelProperty(value="执行结果",required = false)
    private String result;

}