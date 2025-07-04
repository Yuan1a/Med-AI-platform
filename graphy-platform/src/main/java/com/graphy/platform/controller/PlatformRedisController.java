package com.graphy.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.graphy.db.service.TbPlatformConfigService;
import com.graphy.service.bean.OwnResult;

import com.graphy.config.BaseConfig;
import com.graphy.service.service.PlatformCommonService;
import com.graphy.unit.redis.attr.ReidsKeyInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformRedisService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @auther lsd
 * @create 2021-07-29 08:24:23
 * @describe 缓存管理
 */
@Data
@Controller("com.graphy.platform.controller.platformrediscontroller")
@RequiredArgsConstructor
@Api(tags = "缓存管理")
@RequestMapping("/pf/PlatformRedis")
@Slf4j
public class PlatformRedisController {

    private final PlatformRedisService platformRedisService;
    private final PlatformCommonService platformCommonService;
    private final TbPlatformConfigService tbPlatformConfigService;

    /**
     * 缓存管理首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "缓存管理首页", notes = "缓存管理首页")
    private String index(Model model) throws Exception {
        return "/platform/redis/index";
    }

    /**
     * 缓存详细
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ApiOperation(value = "缓存详细", notes = "缓存详细")
    private String info(
            String key,
            Model model) throws Exception {
        String nValue = com.graphy.unit.redis.Api.get(BaseConfig.PRO_KEY, key);
        model.addAttribute("key", key);
        model.addAttribute("value", nValue);
        return "/platform/redis/info";
    }


    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 获取缓存信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getRedisRecord")
    @ApiOperation(value = "获取缓存信息", notes = "获取缓存信息")
    @ResponseBody
    private OwnResult<List<ReidsKeyInfo>> getRedisRecord(@RequestBody Map<String, String> body) throws Exception {
        String key = body.get("key");
        Integer count = null;
        if (!StrUtil.hasEmpty(body.get("count"))) count = Integer.valueOf(body.get("count"));
        return platformRedisService.getRedisRecord(key, count);
    }

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 删除键值
     */
    @RequestMapping(method = RequestMethod.POST, value = "/del")
    @ApiOperation(value = "删除键值", notes = "删除键值")
    @ApiImplicitParams({@ApiImplicitParam(name = "键", value = "key", required = true, dataType = "String")})
    @ResponseBody
    private OwnResult<Boolean> del(String key) throws Exception {
        if (StrUtil.hasEmpty(key)) return OwnResult.error("key不能为空");
        String[] keys = key.split(",");
        for (String item : keys) {
            if (!item.startsWith(BaseConfig.CACHE_PLATFORM_CONFIG_NAME)) {
                com.graphy.unit.redis.Api.remove(BaseConfig.PRO_KEY, item);
            }
        }

        return OwnResult.result(true);
    }


}