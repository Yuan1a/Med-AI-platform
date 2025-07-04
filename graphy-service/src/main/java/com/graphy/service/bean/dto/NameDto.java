package com.graphy.service.bean.dto;
import com.graphy.db.entity.NameEntity;
import lombok.Data;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
* @auther qwt
* @create 2023-06-16 10:43:11
* @describe 响应对象
*/
@Data
@ApiModel(value = "m722038491323564032", description = "对象描述")
public class NameDto  extends NameEntity {


    /**
    * 出身日期
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="出身日期")
    private Date birth;

    /**
    * 姓名
    */
    @ApiModelProperty(value="姓名")
    private String name;

    /**
    * 性别
    */
    @ApiModelProperty(value="性别")
    private String gender;

    /**
    * 诊断医生
    */
    @ApiModelProperty(value="诊断医生")
    private String checkUnitFixedUserName;

    /**
    * 诊断日期
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="诊断日期")
    private Date applyByUnitDate;

}