package com.graphy.service.bean.dto;


import com.graphy.db.entity.TbBusDiagnoseEntity;
import com.graphy.db.entity.TbBusPatientEntity;
import com.graphy.db.entity.TbBusPortraitReportEntity;
import com.graphy.db.entity.TbBusPortraitReportImagesEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* @auther qwt
* @create 2022-12-12 11:16:04
* @describe 上链数据对象
*/
@Data
@ApiModel(value = "m658392626959507456", description = "上链数据对象")
public class OnChainDataDto{

    /**
     * 患者信息
     */
    @ApiModelProperty(value="患者信息")
    private TbBusPatientEntity tbBusPatientEntity;

//    /**
//     * 患者映像报告
//     */
//    @ApiModelProperty(value="患者映像报告")
//    private TbBusPortraitReportEntity tbBusPortraitReportEntity;
    /**
     * 患者映像报告图片记录
     */
    @ApiModelProperty(value="患者映像报告图片记录")
    private List<String> imagesIdList;

//    /**
//     * 患者诊断信息
//     */
//    @ApiModelProperty(value="患者诊断信息")
//    private TbBusDiagnoseEntity tbBusDiagnoseEntity;

}