package com.graphy.service.mapper;
import com.graphy.db.entity.TbBusRegionEntity;

import org.springframework.stereotype.Repository;
import java.util.*;
import org.apache.ibatis.annotations.Param;

/**
 * @auther lsd
 * @create 2022-03-01 14:34:14
 * @describe 行政区划
 */
@Repository("com.graphy.service.mapper.busregionmapper")
public interface BusRegionMapper {

    /**
    * auther： lsd
    * create： 2022-03-01 14:38:53
    * describe： 获取子节点
    * @param pcode   父节点 根节点=0
    * @return
    */
    List<TbBusRegionEntity> getChildren(@Param("pcode") String pcode) throws Exception;

}