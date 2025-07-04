package com.graphy.platform.controller;

import com.graphy.db.entity.TbBusRegionEntity;
import com.graphy.service.bean.OwnResult;

import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.BusRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 * @auther lsd
 * @create 2022-03-01 14:34:13
 * @describe 行政区划
 */
@Data
@Controller("com.graphy.platform.controller.busregioncontroller")
@RequiredArgsConstructor
@Api(tags = "行政区划")
@RequestMapping("/pf/BusRegion")
@Slf4j
public class BusRegionController {

    private final BusRegionService busRegionService;

    /**
     * auther： lsd
     * create： 2022-03-01 14:38:53
     * describe： 获取子节点
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getChildren")
    @ApiOperation(value = "获取子节点", notes = "获取子节点")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pcode", value = "父节点 根节点=0", dataType = "String")
    })
    private OwnResult<List<TbBusRegionEntity>> getChildren(String pcode) throws Exception {
        return busRegionService.getChildren(pcode);
    }
}