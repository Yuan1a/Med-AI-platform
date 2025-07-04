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
 * @describe 系统-表名称实体类
 */
@Data
@TableName("tb_platform_task")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TbPlatformTaskEntity对象", description = "系统-表名称")
public class TbPlatformTaskEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID_", type = IdType.UUID)
    private String id;

    /**
    * 任务名称
    */
    @ApiModelProperty(value = "任务名称")
    @TableField("TASK_NAME_")
    private String taskName;

    /**
    * 计划配置cron表达式
    */
    @ApiModelProperty(value = "计划配置cron表达式")
    @TableField("CRON_")
    private String cron;

    /**
    * bean名称
    */
    @ApiModelProperty(value = "bean名称")
    @TableField("BEAN_NAME_")
    private String beanName;

    /**
    * 函数名称
    */
    @ApiModelProperty(value = "函数名称")
    @TableField("FUN_NAME_")
    private String funName;

    /**
    * 任务日志保留时间(单位:天)
    */
    @ApiModelProperty(value = "任务日志保留时间(单位:天)")
    @TableField("LOG_TIME_OUT_")
    private Integer logTimeOut;

    /**
    * 运行顺序
    */
    @ApiModelProperty(value = "运行顺序")
    @TableField("INDEX_")
    private Integer index;

    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK_")
    private String remark;

    /**
    * 运行状态 run=运行中 pause=暂停
    */
    @ApiModelProperty(value = "运行状态 run=运行中 pause=暂停")
    @TableField("RUN_STATUS_")
    private String runStatus;

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