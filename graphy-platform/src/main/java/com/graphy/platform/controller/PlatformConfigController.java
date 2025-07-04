package com.graphy.platform.controller;

import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformSaveConfigParam;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.db.entity.TbPlatformConfigEntity;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformConfigService;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther lsd
 * @create 2021-08-06 22:56:23
 * @describe 高级配置
 */
@Data
@Controller("com.graphy.platform.controller.platformconfigcontroller")
@RequiredArgsConstructor
@Api(tags = "高级配置")
@RequestMapping("/pf/PlatformConfig")
@Slf4j
public class PlatformConfigController {

    private final PlatformConfigService platformConfigService;

    /**
     * 配置编辑页面
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ApiOperation(value = "配置编辑页面", notes = "配置编辑页面")
    private String edit(Model model) throws Exception {
        TbPlatformConfigEntity orm = platformConfigService.getOne(new LambdaQueryWrapper<TbPlatformConfigEntity>().eq(TbPlatformConfigEntity::getStatus, "1"));
        model.addAttribute("orm", orm);
        return "/platform/config/edit";
    }

    /**
     * auther： lsd
     * create： 2021-08-06 23:11:10
     * describe： 保存配置信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/savePlatformConfig")
    @ApiOperation(value = "保存配置信息", notes = "保存配置信息")
    @ResponseBody
    private OwnResult<Boolean> savePlatformConfig(@RequestBody PlatformSaveConfigParam param) throws Exception {
        return platformConfigService.savePlatformConfig(param);
    }
}