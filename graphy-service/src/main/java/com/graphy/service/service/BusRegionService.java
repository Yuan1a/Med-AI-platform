package com.graphy.service.service;
import com.graphy.service.bean.OwnResult;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graphy.db.entity.TbBusRegionEntity;
import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 14:34:13
 * @describe 行政区划
 */
public interface BusRegionService extends IService<TbBusRegionEntity> {

    /**
    * auther： lsd
    * create： 2022-03-01 14:38:53
    * describe： 获取子节点
    * @param pcode   父节点 根节点=0
    * @return
    */
    OwnResult<List<TbBusRegionEntity>> getChildren(String pcode) throws Exception;
}