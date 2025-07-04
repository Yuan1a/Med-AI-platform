package com.graphy.service.service;

import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.param.PortraitReportRecordParam;
import com.graphy.service.bean.param.SavePortraitReportRecordParam;
import com.graphy.service.bean.dto.PortraitReportRecordDto;
import com.graphy.service.bean.OwnResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbBusPortraitReportEntity;
import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 11:22:46
 * @describe 影像报告
 */
public interface BusPortraitReportService extends IService<TbBusPortraitReportEntity> {

    /**
     * auther： lsd
     * create： 2022-03-01 12:48:05
     * describe： 获取记录
     * @return
     */
    OwnResult<OwnPageResult<PortraitReportRecordDto>> getPortraitReportRecord(PortraitReportRecordParam param) throws Exception;

    /**
     * auther： lsd
     * create： 2022-03-01 12:48:37
     * describe： 保存信息
     * @param param   请求参数
     * @return
     */
    OwnResult<Boolean> savePortraitReportRecord(SavePortraitReportRecordParam param) throws Exception;

    /**
    * auther： lsd
    * create： 2022-03-01 12:48:55
    * describe： 删除信息
    * @param id   主键
    * @return
    */
    OwnResult<Boolean> delPortraitReportRecord(String id) throws Exception;
    /**
     * auther： qwt
     * create： 2022-12-05 12:48:55
     * describe： 区块链提取图片接口
     *
     * @param id 主键
     * @return
     */
    String blockchainImageExtraction(String id) throws Exception;
}