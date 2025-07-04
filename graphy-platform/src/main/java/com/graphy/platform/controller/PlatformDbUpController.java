package com.graphy.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformDbUpPageParam;

import com.graphy.db.entity.TbPlatformDbUpEntity;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformDbUpService;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther lsd
 * @create 2021-08-07 20:24:54
 * @describe 数据日志
 */
@Data
@Controller("com.graphy.platform.controller.platformdbupcontroller")
@RequiredArgsConstructor
@Api(tags = "数据日志")
@RequestMapping("/pf/PlatformDbUp")
@Slf4j
public class PlatformDbUpController {

    private final PlatformDbUpService platformDbUpService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "首页", notes = "首页")
    private String index() throws Exception {
        return "/platform/dbup/index";
    }

    /**
     * 详情页面
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "详情页面", notes = "详情页面")
    private String info(String id, Model model) throws Exception {
        TbPlatformDbUpEntity orm = platformDbUpService.getById(id);
        if (StrUtil.hasEmpty(orm.getHandleStatus())) {
            orm.setHandleStatus("未知");
        } else if (orm.getHandleStatus().equals("1")) {
            orm.setHandleStatus("新增");
        } else if (orm.getHandleStatus().equals("2")) {
            orm.setHandleStatus("修改");
        } else if (orm.getHandleStatus().equals("3")) {
            orm.setHandleStatus("逻辑删除");
        } else if (orm.getHandleStatus().equals("4")) {
            orm.setHandleStatus("物理删除");
        }
        model.addAttribute("orm", orm);
        return "/platform/dbup/info";
    }

    /**
     * auther： lsd
     * create： 2021-08-07 23:08:31
     * describe： 分页获取记录
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getPlatformDbUpPage")
    @ApiOperation(value = "分页获取记录", notes = "分页获取记录")
    @ResponseBody
    private OwnResult<OwnPageResult<TbPlatformDbUpEntity>> getPlatformDbUpPage(@RequestBody PlatformDbUpPageParam param) throws Exception {
        return platformDbUpService.getPlatformDbUpPage(param);
    }
}