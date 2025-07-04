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
 * @describe 系统-任务日志实体类
 */
@Data
@TableName("tb_platform_task_log")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TbPlatformTaskLogEntity对象", description = "系统-任务日志")
public class TbPlatformTaskLogEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID_", type = IdType.UUID)
    private String id;

    /**
    * 任务ID
    */
    @ApiModelProperty(value = "任务ID")
    @TableField("TASK_ID_")
    private String taskId;

    /**
    * 开始执行时间
    */
    @ApiModelProperty(value = "开始执行时间",example="2020-02-01 18:01:01")
    @TableField("RUN_START_TIME_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date runStartTime;

    /**
    * 结束执行时间
    */
    @ApiModelProperty(value = "结束执行时间",example="2020-02-01 18:01:01")
    @TableField("RUN_END_TIME_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date runEndTime;

    /**
    * 发生异常 1=是 0=否
    */
    @ApiModelProperty(value = "发生异常 1=是 0=否")
    @TableField("HAPPEN_ERROR_")
    private String happenError;

    /**
    * 异常信息
    */
    @ApiModelProperty(value = "异常信息")
    @TableField("ERROR_")
    private String error;

    /**
    * 执行结果
    */
    @ApiModelProperty(value = "执行结果")
    @TableField("RESULT_")
    private String result;

    /**
    * 任务记录保留截止时间
    */
    @ApiModelProperty(value = "任务记录保留截止时间",example="2020-02-01 18:01:01")
    @TableField("LOG_TIME_OUT_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logTimeOut;

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