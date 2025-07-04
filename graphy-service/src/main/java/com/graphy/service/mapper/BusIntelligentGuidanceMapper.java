package com.graphy.service.mapper;
import com.graphy.service.bean.dto.IllnessStatisticsDto;
import com.graphy.service.bean.param.IllnessStatisticsParam;

import org.springframework.stereotype.Repository;
import java.util.*;
import org.apache.ibatis.annotations.Param;

/**
 * @auther qwt
 * @create 2023-06-15 09:06:08
 * @describe 智能导诊分析
 */
@Repository("com.graphy.service.mapper.busintelligentguidancemapper")
public interface BusIntelligentGuidanceMapper {

    /**
    * auther： qwt
    * create： 2023-06-15 09:41:48
    * describe： 获取病种信息统计详情
    * @param param   参数
    * @return
    */
    List<IllnessStatisticsDto> getIllnessStatistics(@Param("param") IllnessStatisticsParam param) throws Exception;

}