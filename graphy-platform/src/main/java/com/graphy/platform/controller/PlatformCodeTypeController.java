package com.graphy.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.graphy.db.entity.TbPlatformCodeTypeEntity;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformCodeTypePageParam;
import com.graphy.db.service.TbPlatformCodeTypeService;
import com.graphy.service.service.PlatformCommonService;
import com.graphy.unit.OwnValueText;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformCodeTypeService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @auther lsd
 * @create 2021-07-22 00:48:03
 * @describe 字典类别
 */
@Data
@Controller("com.graphy.platform.controller.platformcodetypecontroller")
@RequiredArgsConstructor
@Api(tags = "字典类别")
@RequestMapping("/pf/PlatformCodeType")
@Slf4j
public class PlatformCodeTypeController {

    private final PlatformCodeTypeService platformCodeTypeService;
    private final TbPlatformCodeTypeService tbPlatformCodeTypeService;
    private final PlatformCommonService platformCommonService;

    /**
     * 字典列表首页
     *
     * @return
     */
    @RequestMapping(value = "/codeindex", method = RequestMethod.GET)
    @ApiOperation(value = "字典列表首页", notes = "字典列表首页")
    private String index(Model model, @RequestParam(value = "type", required = false) String defType) throws Exception {
        List<TbPlatformCodeTypeEntity> listType = tbPlatformCodeTypeService.list(new LambdaQueryWrapper<TbPlatformCodeTypeEntity>().eq(TbPlatformCodeTypeEntity::getStatus, "1").orderByDesc(TbPlatformCodeTypeEntity::getOrd));
        model.addAttribute("type", JSONObject.toJSONString(OwnValueText.list(listType, "code", "name")));
        model.addAttribute("defType", defType);
        if (!StrUtil.hasEmpty(defType)) {
            model.addAttribute("style", "height: 30em;");
        }
        return "/platform/code-type/codeindex";
    }




    /**
     * 字典类别首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "字典类别首页", notes = "字典类别首页")
    private String index() throws Exception {
        return "/platform/code-type/index";
    }

    /**
     * 编辑页面
     *
     * @param id    主键
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String")})
    @ApiOperation(value = "编辑页面", notes = "编辑页面")
    public String edit(@RequestParam(value = "id", required = false) String id, Model model) throws Exception {
        TbPlatformCodeTypeEntity orm = new TbPlatformCodeTypeEntity();
        if (!StrUtil.hasEmpty(id)) {
            model.addAttribute("orm", tbPlatformCodeTypeService.getById(id));
        } else {
            model.addAttribute("orm", orm);
        }
        return "/platform/code-type/edit";
    }


    /**
     * auther： lsd
     * create： 2021-07-24 13:16:36
     * describe： 分页获取字典类别信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getCodeTypePage")
    @ApiOperation(value = "分页获取字典类别信息", notes = "分页获取字典类别信息")
    @ResponseBody
    private OwnResult<OwnPageResult<TbPlatformCodeTypeEntity>> getCodeTypePage(@RequestBody PlatformCodeTypePageParam param) throws Exception {
        return platformCodeTypeService.getCodeTypePage(param);
    }

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 删除字典类别
     */
    @RequestMapping(method = RequestMethod.POST, value = "/del")
    @ApiOperation(value = "删除字典类别", notes = "删除字典类别")
    @ApiImplicitParams({@ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String")})
    @ResponseBody
    private OwnResult<Boolean> del(String id) throws Exception {
        TbPlatformCodeTypeEntity delEntity = new TbPlatformCodeTypeEntity();
        delEntity.setStatus("0");
        boolean ok = tbPlatformCodeTypeService.update(delEntity, new LambdaUpdateWrapper<TbPlatformCodeTypeEntity>().eq(TbPlatformCodeTypeEntity::getId, id));
        return OwnResult.result(ok);
    }

    /**
     * auther： lsd
     * create： 2021-07-24 13:20:39
     * describe： 保存字典类别信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveCodeType")
    @ApiOperation(value = "保存字典类别信息", notes = "保存字典类别信息")
    @ResponseBody
    private OwnResult<Boolean> saveCodeType(@RequestBody TbPlatformCodeTypeEntity param) throws Exception {
        return platformCodeTypeService.saveCodeType(param);
    }
}