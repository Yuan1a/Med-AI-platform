package com.graphy.service.bean.param;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusPatientEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
* @auther lsd
* @create 2022-03-01 11:54:46
* @describe 请求参数
*/
@Data
@ApiModel(value = "SavePatientRecordParam对象", description = "请求参数")
public class SavePatientRecordParam extends TbBusPatientEntity  {

    /**
     * 报告时间
     */
    @ApiModelProperty(value = "诊断时间",example="2020-02-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reportTime;
    /**
     * 报告机构
     */
    @ApiModelProperty(value = "报告机构")
    private String reportUnit;
    /**
     * 报告医生
     */
    @ApiModelProperty(value = "报告医生")
    private String reportDoctor;
    /**
     * 放射号
     */
    @ApiModelProperty(value = "放射号")
    private String radiationId;
    /**
     * 影像报告正文
     */
    @ApiModelProperty(value = "影像报告正文")
    private String straightMatter;
    /**
     * 影像报告图片
     */
    @ApiModelProperty(value = "影像报告图片")
    private List<String> files;

}