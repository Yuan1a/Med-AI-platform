package com.graphy.platform.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.graphy.service.bean.dto.PlatformModuleListDto;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformModuleListParam;
import cn.hutool.core.util.StrUtil;
import com.graphy.db.entity.TbPlatformModuleEntity;
import com.graphy.db.service.TbPlatformModuleService;
import com.graphy.service.service.PlatformCommonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformModuleService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @auther lsd
 * @create 2021-07-22 00:48:24
 * @describe 页面导航
 */
@Data
@Controller("com.graphy.platform.controller.platformmodulecontroller")
@RequiredArgsConstructor
@Api(tags = "页面导航")
@RequestMapping("/pf/PlatformModule")
@Slf4j
public class PlatformModuleController {
    @Value("${server.servlet.context-path}")
    private String webLevel;
    private final PlatformModuleService platformModuleService;

    private final TbPlatformModuleService tbPlatformModuleService;
    private final PlatformCommonService platformCommonService;

    /**
     * 图标选择
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/icon", method = RequestMethod.GET)
    private String icon(Model model, HttpServletRequest request) throws Exception {
        String alibabaFontsOwnBaseUrl = webLevel+"/js/own/fonts/own-framework/iconfont.css";
        String host = com.graphy.unit.http.Api.getHost(request);
        List<String> icons = com.graphy.unit.alibabafonts.Api.getAliyunIcons(host + alibabaFontsOwnBaseUrl);
        model.addAttribute("icons", icons);
        return "/platform/module/icon";
    }

    /**
     * 导航首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "导航首页", notes = "导航首页")
    private String index() throws Exception {
        return "/platform/module/index";
    }

    /**
     * 添加/编辑页面
     *
     * @param id    主键
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "主键", value = "id", required = false, dataType = "String"), @ApiImplicitParam(name = "父导航", value = "pid", required = true, dataType = "String")})
    @ApiOperation(value = "添加/编辑页面", notes = "添加/编辑页面")
    public String edit(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "pid", required = true) String pid, Model model) throws Exception {
        TbPlatformModuleEntity orm = null;
        TbPlatformModuleEntity porm = null;
        if (!StrUtil.hasEmpty(id)) {
            orm = tbPlatformModuleService.getById(id);
        }
        if (!StrUtil.hasEmpty(pid) && !pid.equals("0")) {
            porm = tbPlatformModuleService.getById(pid);
        }
        if (orm == null) {
            orm = new TbPlatformModuleEntity();
            orm.setShowInMenu("1");
            orm.setOrd(0);
            orm.setIsNewPage("0");
            orm.setIsUse("1");
        }
        if (porm == null) {
            porm = new TbPlatformModuleEntity();
            porm.setId("0");
            porm.setName("根导航");
        }
        model.addAttribute("orm", orm);
        model.addAttribute("porm", porm);
        return "/platform/module/edit";
    }

    /**
     * auther： lsd
     * create： 2021-07-24 17:04:19
     * describe： 获取导航列表数据
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getModuleList")
    @ApiOperation(value = "获取导航列表数据", notes = "获取导航列表数据")
    @ResponseBody
    private OwnResult<List<PlatformModuleListDto>> getModuleList(@RequestBody PlatformModuleListParam param) throws Exception {
        return platformModuleService.getModuleList(param);
    }

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 删除导航
     */
    @RequestMapping(method = RequestMethod.POST, value = "/del")
    @ApiOperation(value = "删除导航", notes = "删除导航")
    @ApiImplicitParams({@ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String")})
    @ResponseBody
    private OwnResult<Boolean> del(String id) throws Exception {
        TbPlatformModuleEntity delEntity = new TbPlatformModuleEntity();
        delEntity.setStatus("0");
        boolean ok = tbPlatformModuleService.update(delEntity, new LambdaUpdateWrapper<TbPlatformModuleEntity>().eq(TbPlatformModuleEntity::getId, id));
        return OwnResult.result(ok);
    }

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 修改排序
     */
    @RequestMapping(method = RequestMethod.POST, value = "/ord")
    @ApiOperation(value = "修改排序", notes = "修改排序")
    @ApiImplicitParams({@ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String"), @ApiImplicitParam(name = "排序", value = "ord", required = true, dataType = "Integer")})
    @ResponseBody
    private OwnResult<Boolean> ord(String id, Integer ord) throws Exception {
        TbPlatformModuleEntity entity = new TbPlatformModuleEntity();
        entity.setOrd(ord);
        boolean ok = tbPlatformModuleService.update(entity, new LambdaUpdateWrapper<TbPlatformModuleEntity>().eq(TbPlatformModuleEntity::getId, id));
        return OwnResult.result(ok);
    }

    /**
     * auther： lsd
     * create： 2021-07-24 23:05:20
     * describe： 保存导航信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveModule")
    @ApiOperation(value = "保存导航信息", notes = "保存导航信息")
    @ResponseBody
    private OwnResult<Boolean> saveModule(@RequestBody TbPlatformModuleEntity param) throws Exception {
        return platformModuleService.saveModule(param);
    }
}