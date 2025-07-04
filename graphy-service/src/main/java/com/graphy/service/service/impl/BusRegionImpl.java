package com.graphy.service.service.impl;
import com.graphy.service.bean.OwnResult;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.BusRegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbBusRegionMapper;
import com.graphy.db.entity.TbBusRegionEntity;
import com.graphy.service.service.BusRegionService;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;

/**
 * @auther lsd
 * @create 2022-03-01 14:34:14
 * @describe 行政区划
 */
@Service("com.graphy.service.service.impl.busregionimpl")
@RequiredArgsConstructor
@Slf4j
public class BusRegionImpl extends ServiceImpl<TbBusRegionMapper, TbBusRegionEntity> implements BusRegionService {

    private final BusRegionMapper busRegionMapper;

    /**
    * auther： lsd
    * create： 2022-03-01 14:38:53
    * describe： 获取子节点
    * @param pcode   父节点 根节点=0
    * @return
    */
    public OwnResult<List<TbBusRegionEntity>> getChildren(String pcode) throws Exception {
        List<TbBusRegionEntity> list = busRegionMapper.getChildren(pcode);
        return OwnResult.result(list);
    }

}