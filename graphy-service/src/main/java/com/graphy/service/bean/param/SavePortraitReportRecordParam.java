package com.graphy.service.bean.param;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import com.graphy.db.entity.TbBusPortraitReportEntity;

import java.util.List;

/**
* @auther lsd
* @create 2022-03-01 12:48:37
* @describe 请求参数
*/
@Data
@ApiModel(value = "SavePortraitReportRecordParam对象", description = "请求参数")
public class SavePortraitReportRecordParam extends TbBusPortraitReportEntity  {

    /**
     * 影像报告图片
     */
    @ApiModelProperty(value = "影像报告图片")
    private List<String> files;

}