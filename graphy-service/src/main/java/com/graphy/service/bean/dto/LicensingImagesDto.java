package com.graphy.service.bean.dto;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusLicensingApplyEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* @auther qwt
* @create 2022-12-08 08:42:47
* @describe 响应对象
*/
@Data
@ApiModel(value = "m653154500390944768", description = "对象描述")
public class LicensingImagesDto extends TbBusLicensingApplyEntity  {
    /**
     * 放射号
     */
    @ApiModelProperty(value = "放射号")
    private String radiationId;

    /**
     * 性别 1=男 2=女
     */
    @ApiModelProperty(value = "性别 1=男 2=女")
    private String gender;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;
    /**
     * 授权机构名称
     */
    @ApiModelProperty(value = "授权机构名称")
    private String approvalUnitName;
    /**
     * 申请机构名称
     */
    @ApiModelProperty(value = "申请机构名称")
    private String applyUnitName;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="申请时间",required = false)
    private Date applyTime;
}