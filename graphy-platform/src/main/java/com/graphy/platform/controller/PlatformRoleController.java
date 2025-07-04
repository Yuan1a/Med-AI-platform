package com.graphy.platform.controller;

import com.graphy.service.bean.dto.PlatformRolePowerDto;
import com.graphy.service.bean.param.PlatformRolePowerParam;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.graphy.db.entity.TbPlatformRoleEntity;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformRolePageParam;
import com.graphy.db.service.TbPlatformRoleService;
import com.graphy.service.service.PlatformCommonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformRoleService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @auther lsd
 * @create 2021-07-22 00:48:57
 * @describe 角色管理
 */
@Data
@Controller("com.graphy.platform.controller.platformrolecontroller")
@RequiredArgsConstructor
@Api(tags = "角色管理")
@RequestMapping("/pf/PlatformRole")
@Slf4j
public class PlatformRoleController {

    private final PlatformRoleService platformRoleService;

    private final TbPlatformRoleService tbPlatformRoleService;
    private final PlatformCommonService platformCommonService;

    /**
     * 角色首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "角色首页", notes = "角色首页")
    private String index(Model model) throws Exception {
        return "/platform/role/index";
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
        TbPlatformRoleEntity orm = new TbPlatformRoleEntity();
        if (!StrUtil.hasEmpty(id)) {
            model.addAttribute("orm", platformRoleService.getById(id));
        } else {
            model.addAttribute("orm", orm);
        }
        return "/platform/role/edit";
    }

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 删除角色
     */
    @RequestMapping(method = RequestMethod.POST, value = "/del")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @ApiImplicitParams({@ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String")})
    @ResponseBody
    private OwnResult<Boolean> del(String id) throws Exception {
        TbPlatformRoleEntity delEntity = new TbPlatformRoleEntity();
        delEntity.setStatus("0");
        boolean ok = platformRoleService.update(delEntity, new LambdaUpdateWrapper<TbPlatformRoleEntity>().eq(TbPlatformRoleEntity::getId, id));
        return OwnResult.result(ok);
    }


    /**
     * auther： lsd
     * create： 2021-07-25 14:08:25
     * describe： 分页获取角色信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getRolePage")
    @ApiOperation(value = "分页获取角色信息", notes = "分页获取角色信息")
    @ResponseBody
    private OwnResult<OwnPageResult<TbPlatformRoleEntity>> getRolePage(@RequestBody PlatformRolePageParam param) throws Exception {
        return platformRoleService.getRolePage(param);
    }

    /**
     * auther： lsd
     * create： 2021-07-25 14:09:21
     * describe： 保存角色信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveRole")
    @ApiOperation(value = "保存角色信息", notes = "保存角色信息")
    @ResponseBody
    private OwnResult<Boolean> saveRole(@RequestBody TbPlatformRoleEntity param) throws Exception {
        return platformRoleService.saveRole(param);
    }


    /**
     * 权限管理
     *
     * @return
     */
    @RequestMapping(value = "/power", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "角色ID", value = "roleId", required = true, dataType = "String")
    })
    @ApiOperation(value = "权限管理", notes = "权限管理")
    private String power(String roleId, Model model) throws Exception {
        model.addAttribute("roleId", roleId);
        return "/platform/role/power";
    }

    /**
     * auther： lsd
     * create： 2021-07-25 14:45:48
     * describe： 获取角色权限信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getRolePower")
    @ApiOperation(value = "获取角色权限信息", notes = "获取角色权限信息")
    @ResponseBody
    private OwnResult<List<PlatformRolePowerDto>> getRolePower(@RequestBody PlatformRolePowerParam param) throws Exception {
        return platformRoleService.getRolePower(param);
    }


    /**
     * auther： lsd
     * create： 2021-07-25 14:09:21
     * describe： 保存角色权限信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveRolePower")
    @ApiOperation(value = "保存角色权限信息", notes = "保存角色权限信息")
    @ResponseBody
    private OwnResult<Boolean> saveRolePower(@RequestBody TbPlatformRoleEntity param) throws Exception {
        Boolean ok = platformRoleService.update(param, new LambdaQueryWrapper<TbPlatformRoleEntity>().eq(TbPlatformRoleEntity::getId, param.getId()));
        return OwnResult.result(ok);
    }


}