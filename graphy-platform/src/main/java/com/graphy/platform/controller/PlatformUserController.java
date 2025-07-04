package com.graphy.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import com.graphy.db.entity.*;
import com.graphy.db.service.TbBusUnitService;
import com.graphy.db.service.TbPlatformCodeService;
import com.graphy.service.bean.dto.PlatformUserPageDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.param.PlatformUserPageParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.service.bean.OwnResult;
import com.graphy.db.service.TbPlatformRoleService;
import com.graphy.db.service.TbPlatformUserService;
import com.graphy.config.BaseConfig;
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
import com.graphy.service.service.PlatformUserService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @auther lsd
 * @create 2021-07-22 00:49:32
 * @describe 用户管理
 */
@Data
@Controller("com.graphy.platform.controller.platformusercontroller")
@RequiredArgsConstructor
@Api(tags = "用户管理")
@RequestMapping("/pf/PlatformUser")
@Slf4j
public class PlatformUserController {

    private final PlatformUserService platformUserService;

    private final TbPlatformUserService tbPlatformUserService;

    private final TbPlatformRoleService tbPlatformRoleService;
    private final PlatformCommonService platformCommonService;
    private final TbPlatformCodeService tbPlatformCodeService;
    private final TbBusUnitService tbBusUnitService;
    /**
     * 加载页面基础数据源
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "/loadBaseData")
    @ResponseBody
    public OwnResult<Map<String, Object>> loadBaseData() throws Exception {
        List<TbPlatformRoleEntity> roles = tbPlatformRoleService.list(new LambdaQueryWrapper<TbPlatformRoleEntity>().eq(TbPlatformRoleEntity::getStatus, "1").orderByDesc(TbPlatformRoleEntity::getCtime));
        Map<String, Object> maps = new LinkedHashMap<>();
        maps.put("roles", roles);
        return OwnResult.result(maps);
    }

    /**
     * 用户首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "用户首页", notes = "用户首页")
    private String index(Model model) throws Exception {
        return "/platform/user/index";
    }

    /**
     * 编辑用户
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "id", required = false) String id, Model model) throws Exception {
        TbPlatformUserEntity orm = new TbPlatformUserEntity();
        TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
        if (id != null && !id.equals("")) {
            orm = tbPlatformUserService.getById(id);
            orm.setPassword(com.graphy.unit.crypt.Api.decrypt(orm.getPassword(), BaseConfig.CRYPT_KEY));
            model.addAttribute("orm", orm);
        } else {
            orm.setPassword(configEntity.getPlatformSafetyPasswordDefault());
            model.addAttribute("orm", orm);
        }
        String passwordLevelDescribe = "";
        if (configEntity.getPlatformSafetyPasswordLevel().equals(2)) {
            passwordLevelDescribe += ",并包含数字,字母";
        } else if (configEntity.getPlatformSafetyPasswordLevel().equals(3)) {
            passwordLevelDescribe += ",并包含数字,字母,特殊字符";
        } else if (configEntity.getPlatformSafetyPasswordLevel().equals(4)) {
            passwordLevelDescribe += ",并包含数字,大写字母,小写字符,特殊字符";
        }
        String unit = JSONObject.toJSONString(OwnValueText.list(tbBusUnitService.list(new LambdaQueryWrapper<TbBusUnitEntity>()
                .eq(TbBusUnitEntity::getStatus, "1")
        ), "id", "name"));

        List<TbPlatformCodeEntity> userTypeCode = tbPlatformCodeService.list(new LambdaQueryWrapper<TbPlatformCodeEntity>().eq(TbPlatformCodeEntity::getStatus, "1")
                .eq(TbPlatformCodeEntity::getType, "user-type")
                .orderByAsc(TbPlatformCodeEntity::getOrd));
        model.addAttribute("userType",JSONObject.toJSONString(OwnValueText.list(userTypeCode, "code", "name")));
        model.addAttribute("unit", unit);
        model.addAttribute("passwordLevelDescribe", passwordLevelDescribe);
        model.addAttribute("defPasswordMinLength", configEntity.getPlatformSafetyPasswordMinLength());
        return "/platform/user/edit";
    }

    /**
     * auther： lsd
     * create： 2021-07-26 09:03:18
     * describe： 分页获取用户信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getUserPage")
    @ApiOperation(value = "分页获取用户信息", notes = "分页获取用户信息")
    @ResponseBody
    private OwnResult<OwnPageResult<PlatformUserPageDto>> getUserPage(@RequestBody PlatformUserPageParam param) throws Exception {
        return platformUserService.getUserPage(param);
    }

    /**
     * 修改启用状态
     * <p>创建人：林诗达</p>
     * <p>创建时间：2019-12-13 11:28:02</p>
     *
     * @param id    用户ID
     * @param isUse 启用 1=是 0=否
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/upUserIsUse")
    @ApiOperation(value = "修改启用状态", notes = "修改启用状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String"), @ApiImplicitParam(name = "启用 1=是 0=否", value = "isUse", required = true, dataType = "String")})
    @ResponseBody
    public OwnResult<Boolean> upUserIsUse(String id, String isUse) throws Exception {
        TbPlatformUserEntity entity = new TbPlatformUserEntity();
        entity.setIsUse(isUse);
        boolean ok = platformUserService.update(entity, new LambdaUpdateWrapper<TbPlatformUserEntity>().eq(TbPlatformUserEntity::getId, id));
        return OwnResult.result(ok);
    }

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 删除用户
     */
    @RequestMapping(method = RequestMethod.POST, value = "/del")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String")})
    @ResponseBody
    private OwnResult<Boolean> del(String id) throws Exception {
        TbPlatformUserEntity entity = new TbPlatformUserEntity();
        entity.setStatus("0");
        boolean ok = platformUserService.update(entity, new LambdaUpdateWrapper<TbPlatformUserEntity>().eq(TbPlatformUserEntity::getId, id));
        return OwnResult.result(ok);
    }

    /**
     * auther： lsd
     * create： 2021-07-26 10:53:50
     * describe： 保存用户信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveUser")
    @ApiOperation(value = "保存用户信息", notes = "保存用户信息")
    @ResponseBody
    private OwnResult<Boolean> saveUser(@RequestBody TbPlatformUserEntity param) throws Exception {
        return platformUserService.saveUser(param);
    }
}