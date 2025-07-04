package com.graphy.unit.ocr.aly.attr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 结婚证ocr解析结果
 */
@ApiModel(description = "结婚证ocr解析结果")
public class MarryResult implements Serializable {

    /**
     * 结婚证号
     */
    @ApiModelProperty(value = "结婚证号", example = "")
    public String marryNo;
    /**
     * 女方-姓名
     */
    @ApiModelProperty(value = "女方-姓名", example = "")
    public String wName;
    /**
     * 女方-国籍
     */
    @ApiModelProperty(value = "女方-国籍", example = "")
    public String wNationality;
    /**
     * 女方-出生
     */
    @ApiModelProperty(value = "女方-出生", example = "")
    public String wBirth;
    /**
     * 女方-证件
     */
    @ApiModelProperty(value = "女方-证件", example = "")
    public String wCert;

    /**
     * 男方-姓名
     */
    @ApiModelProperty(value = "男方-姓名", example = "")
    public String mName;
    /**
     * 男方-国籍
     */
    @ApiModelProperty(value = "男方-国籍", example = "")
    public String mNationality;
    /**
     * 男方-出生
     */
    @ApiModelProperty(value = "男方-出生", example = "")
    public String mBirth;
    /**
     * 男方-证件
     */
    @ApiModelProperty(value = "男方-证件", example = "")
    public String mCert;

    public String getMarryNo() {
        return marryNo;
    }

    public void setMarryNo(String marryNo) {
        this.marryNo = marryNo;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName;
    }

    public String getwNationality() {
        return wNationality;
    }

    public void setwNationality(String wNationality) {
        this.wNationality = wNationality;
    }

    public String getwBirth() {
        return wBirth;
    }

    public void setwBirth(String wBirth) {
        this.wBirth = wBirth;
    }

    public String getwCert() {
        return wCert;
    }

    public void setwCert(String wCert) {
        this.wCert = wCert;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNationality() {
        return mNationality;
    }

    public void setmNationality(String mNationality) {
        this.mNationality = mNationality;
    }

    public String getmBirth() {
        return mBirth;
    }

    public void setmBirth(String mBirth) {
        this.mBirth = mBirth;
    }

    public String getmCert() {
        return mCert;
    }

    public void setmCert(String mCert) {
        this.mCert = mCert;
    }
}
