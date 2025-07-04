package com.graphy.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.graphy.service.bean.dto.PlatformCodePageDto;
import com.graphy.service.bean.param.PlatformCodePageParam;
import com.graphy.service.bean.OwnPageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.service.bean.OwnResult;
import com.graphy.db.entity.TbPlatformCodeEntity;
import com.graphy.db.entity.TbPlatformCodeTypeEntity;
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
import com.graphy.service.service.PlatformCodeService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @auther lsd
 * @create 2021-07-22 00:47:45
 * @describe 字典管理
 */
@Data
@Controller("com.graphy.platform.controller.platformcodecontroller")
@RequiredArgsConstructor
@Api(tags = "字典管理")
@RequestMapping("/pf/PlatformCode")
@Slf4j
public class PlatformCodeController {

    private final PlatformCodeService platformCodeService;
    private final TbPlatformCodeTypeService tbPlatformCodeTypeService;
    private final PlatformCommonService platformCommonService;


    /**
     * 字典首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "字典首页", notes = "字典首页")
    private String index(Model model, @RequestParam(value = "type", required = false) String defType) throws Exception {
        List<TbPlatformCodeTypeEntity> listType = tbPlatformCodeTypeService.list(new LambdaQueryWrapper<TbPlatformCodeTypeEntity>().eq(TbPlatformCodeTypeEntity::getStatus, "1").orderByDesc(TbPlatformCodeTypeEntity::getOrd));
        model.addAttribute("type", JSONObject.toJSONString(OwnValueText.list(listType, "code", "name")));
        model.addAttribute("defType", defType);
        if (!StrUtil.hasEmpty(defType)) {
            model.addAttribute("style", "height: 30em;");
        }
        return "/platform/code/index";
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
    public String edit(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "defType", required = false) String defType,
            Model model) throws Exception {
        TbPlatformCodeEntity orm = new TbPlatformCodeEntity();
        if (!StrUtil.hasEmpty(id)) {
            orm = platformCodeService.getById(id);
        } else {
            orm.setOrd(0);
        }
        model.addAttribute("orm", orm);
        List<TbPlatformCodeTypeEntity> listType = tbPlatformCodeTypeService.list(new LambdaQueryWrapper<TbPlatformCodeTypeEntity>().eq(TbPlatformCodeTypeEntity::getStatus, "1").orderByDesc(TbPlatformCodeTypeEntity::getOrd));
        model.addAttribute("type", listType);

        if (!StrUtil.hasEmpty(id)) {
            model.addAttribute("defType", orm.getType());
        } else if (!StrUtil.hasEmpty(defType)) {
            model.addAttribute("defType", defType);
        }
        return "/platform/code/edit";
    }

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 删除字典
     */
    @RequestMapping(method = RequestMethod.POST, value = "/del")
    @ApiOperation(value = "删除字典", notes = "删除字典")
    @ApiImplicitParams({@ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String")})
    @ResponseBody
    private OwnResult<Boolean> del(String id) throws Exception {
        TbPlatformCodeEntity delEntity = new TbPlatformCodeEntity();
        delEntity.setStatus("0");
        boolean ok = platformCodeService.update(delEntity, new LambdaUpdateWrapper<TbPlatformCodeEntity>().eq(TbPlatformCodeEntity::getId, id));
        return OwnResult.result(ok);
    }

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 分页获取字典信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getCodePage")
    @ApiOperation(value = "分页获取字典信息", notes = "分页获取字典信息")
    @ResponseBody
    private OwnResult<OwnPageResult<PlatformCodePageDto>> getCodePage(@RequestBody PlatformCodePageParam param) throws Exception {
        return platformCodeService.getCodePage(param);
    }

    /**
     * auther： lsd
     * create： 2021-07-23 23:53:58
     * describe： 保存字典信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveCode")
    @ApiOperation(value = "保存字典信息", notes = "保存字典信息")
    @ResponseBody
    private OwnResult<Boolean> saveCode(@RequestBody TbPlatformCodeEntity param) throws Exception {
        return platformCodeService.saveCode(param);
    }
}