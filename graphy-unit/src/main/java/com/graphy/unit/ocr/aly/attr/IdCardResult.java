package com.graphy.unit.ocr.aly.attr;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "证件解析对象-基础信息")
public class IdCardResult {


    /**
     * 证件地址
     */
    @ApiModelProperty(value = "证件地址", example = "海南省海口市秀英区海秀路华运小区H栋2101室")
    public String address;
    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "出生日期", example = "1987-02-09")
    public Date birthday;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", example = "杨彩霞")
    public String name;
    /**
     * 身份证号码
     */
    @ApiModelProperty(value = "身份证号码", example = "46002819870909889218")
    public String code;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", example = "女")
    public String sex;
    /**
     * 民族
     */
    @ApiModelProperty(value = "民族", example = "汉族")
    public String nation;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别编码", example = "2")
    public String gender;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别名称", example = "女")
    public String genderName;
    /**
     * 身份证头像(base64)
     */
    @ApiModelProperty(value = "身份证头像(base64)")
    public String headPhoto;

    public String getGender() {
        if (this.sex != null && !this.sex.equals("")) {
            return this.sex.equals("男") ? "1" : "2";
        }
        return sex;
    }

    public void setGender(String gender) {
        this.sex = gender;
    }

    public String getGenderName() {
        return this.sex;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }
}
