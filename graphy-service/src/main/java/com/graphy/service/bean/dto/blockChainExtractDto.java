package com.graphy.service.bean.dto;

import com.graphy.db.entity.TbBusDiagnoseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* @auther qwt
* @create 2022-12-02 12:52:43
* @describe 区块链提取文件接收对象
*/
@Data
@ApiModel(value = "blockChainExtractDto", description = "区块链提取文件接收对象")
public class blockChainExtractDto {


    /**
    * 存证索引ID(为影响图片表ID)
    */
    @ApiModelProperty(value="存证索引ID")
    private String DepositCertID;

    /**
    * 存证文件名
    */
    @ApiModelProperty(value="存证文件名")
    private String FileName;

    /**
    * 文件类型
    */
    @ApiModelProperty(value="文件类型")
    private String FileType;

    /**
    *
    */
    @ApiModelProperty(value="")
    private String Mode;
    /**
     *
     */
    @ApiModelProperty(value="")
    private String FileSize;
    /**
     *图片哈希加密后的字符
     */
    @ApiModelProperty(value="图片哈希加密后的字符")
    private String FileHash;
    /**
     *
     */
    @ApiModelProperty(value="")
    private String CertTime;
    /**
     *
     */
    @ApiModelProperty(value="")
    private String CreateTime;
}