package com.graphy.service.bean.param;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import com.graphy.service.bean.OwnPageParam;

/**
* @auther qwt
* @create 2022-12-08 08:42:47
* @describe 请求参数
*/
@Data
@ApiModel(value = "m653154500319641600", description = "对象描述")
public class LicensingImagesParam extends OwnPageParam  {
    /**
     * 放射号
     */
    @ApiModelProperty(value = "放射号")
    private String radiationId;

    /**
    * 主键
    */
    @ApiModelProperty(value="主键",required = false)
    private String id;

    /**
    * 患者ID
    */
    @ApiModelProperty(value="",required = false)
    private String patientId;

    /**
    * 申请机构
    */
    @ApiModelProperty(value="申请机构",required = false)
    private String applyUnit;

    /**
    * 审批机构
    */
    @ApiModelProperty(value="审批机构",required = false)
    private String approvalUnit;

    /**
    * 审批状态
    */
    @ApiModelProperty(value="审批状态",required = false)
    private String approvalStatus;

    /**
    * 审批时间-开始
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="审批时间-开始",required = false)
    private Date approvalTimeStart;

    /**
    * 审批时间-截止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="审批时间-截止",required = false)
    private Date approvalTimeEnd;

    /**
     * 申请时间-开始
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="申请时间-开始",required = false)
    private Date ctimeStart;

    /**
     * 申请时间-截止
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="申请时间-截止",required = false)
    private Date ctimeEnd;
}