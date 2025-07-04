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
 * @auther lsd
 * @create 2022-03-01 17:00:11
 * @describe 业务-机构信息实体类
 */
@Data
@TableName("tb_bus_unit")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TbBusUnitEntity对象", description = "业务-机构信息")
public class TbBusUnitEntity implements Serializable{

    private static final long serialVersionUID=1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    @TableId(value = "ID_", type = IdType.UUID)
    private String id;

    /**
    * 编码
    */
    @ApiModelProperty(value = "编码")
    @TableField("CODE_")
    private String code;

    /**
    * 名称
    */
    @ApiModelProperty(value = "名称")
    @TableField("NAME_")
    private String name;

    /**
    * 简称
    */
    @ApiModelProperty(value = "简称")
    @TableField("NAME_SHORT")
    private String nameShort;

    /**
    * 机构类型 关联字典unit-type类别
    */
    @ApiModelProperty(value = "机构类型 关联字典unit-type类别")
    @TableField("TYPE_")
    private String type;

    /**
    * 医院级别 关联字典unit-grade类别
    */
    @ApiModelProperty(value = "医院级别 关联字典unit-grade类别")
    @TableField("GRADE_")
    private String grade;

    /**
    * 所属省
    */
    @ApiModelProperty(value = "所属省")
    @TableField("PROV_")
    private String prov;

    /**
    * 所属市
    */
    @ApiModelProperty(value = "所属市")
    @TableField("CITY_")
    private String city;

    /**
    * 所属县
    */
    @ApiModelProperty(value = "所属县")
    @TableField("COUNTY_")
    private String county;

    /**
    * 所属镇
    */
    @ApiModelProperty(value = "所属镇")
    @TableField("TOWN_")
    private String town;

    /**
    * 详细地址 例如：长滨路29号
    */
    @ApiModelProperty(value = "详细地址 例如：长滨路29号")
    @TableField("ADDR_")
    private String addr;

    /**
    * 长地址
    */
    @ApiModelProperty(value = "长地址")
    @TableField("ADDRESS_")
    private String address;

    /**
    * 联系人
    */
    @ApiModelProperty(value = "联系人")
    @TableField("LINKMAN_")
    private String linkman;

    /**
    * 联系手机
    */
    @ApiModelProperty(value = "联系手机")
    @TableField("MOBILE_")
    private String mobile;

    /**
    * 固定电话
    */
    @ApiModelProperty(value = "固定电话")
    @TableField("PHONE_")
    private String phone;

    /**
    * 放射医学设备概况
    */
    @ApiModelProperty(value = "放射医学设备概况")
    @TableField("GENERAL_")
    private String general;

    /**
    * 机构简介
    */
    @ApiModelProperty(value = "机构简介")
    @TableField("INTRODUCE_")
    private String introduce;

    /**
    * 状态 0=删除 1=有效
    */
    @ApiModelProperty(value = "状态 0=删除 1=有效",hidden = true)
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