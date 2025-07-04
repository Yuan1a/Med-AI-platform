package com.graphy.service.service;
import com.graphy.service.bean.param.IntelligentImageAnalysiParam;

import com.graphy.service.bean.dto.IllnessStatisticsDto;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.IllnessStatisticsParam;
import java.util.*;

/**
 * @auther qwt
 * @create 2023-06-15 09:06:08
 * @describe 智能导诊分析
 */
public interface BusIntelligentGuidanceService {

    /**
     * auther： qwt
     * create： 2023-06-15 09:41:48
     * describe： 获取病种信息统计详情
     * @param param   参数
     * @return
     */
    OwnResult<List<IllnessStatisticsDto>> getIllnessStatistics(IllnessStatisticsParam param) throws Exception;

    /**
    * auther： qwt
    * create： 2023-11-15 11:56:00
    * describe： 影像图片智能分析
    * @param param   请求参数
    * @return
    */
    OwnResult<String> intelligentImageAnalysi(IntelligentImageAnalysiParam param) throws Exception;
}