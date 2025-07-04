package com.graphy.service.mapper;
import com.graphy.service.bean.dto.IllnessRecordDto;
import com.graphy.service.bean.param.IllnessRecordParam;

import org.springframework.stereotype.Repository;
import java.util.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2022-03-01 11:21:35
 * @describe 病种管理
 */
@Repository("com.graphy.service.mapper.busillnessmapper")
public interface BusIllnessMapper {

    /**
    * auther： lsd
    * create： 2022-03-01 12:42:07
    * describe： 获取病种信息
    * @param param   参数
    * @return
    */
    IPage<IllnessRecordDto> getIllnessRecord(Page<?> page, @Param("param") IllnessRecordParam param) throws Exception;

}