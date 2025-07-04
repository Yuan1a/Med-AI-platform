package com.graphy.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @auther qwt
 * @create 2023-06-16 09:26:34
 * @describe 实体类
 */
@Data
@TableName("name")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "NameEntity对象", description = "")
public class NameEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    @TableField("AGE_")
    private String age;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    /**
    * 姓名
    */
    @ApiModelProperty(value = "姓名")
    @TableField("NAME_")
    private String name;

    /**
    * 性别
    */
    @ApiModelProperty(value = "性别")
    @TableField("GENDER_")
    private String gender;

    /**
    * 诊断医生
    */
    @ApiModelProperty(value = "诊断医生")
    @TableField("CHECK_UNIT_FIXED_USER_NAME_")
    private String checkUnitFixedUserName;

    /**
    * 诊断日期
    */
    @ApiModelProperty(value = "诊断日期",example="2020-02-01 18:01:01")
    @TableField("APPLY_BY_UNIT_DATE_")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyByUnitDate;
    /**
     * 放射号
     */
    @ApiModelProperty(value = "放射号")
    @TableField("RADIATION_ID_")
    private String radiationId;

}