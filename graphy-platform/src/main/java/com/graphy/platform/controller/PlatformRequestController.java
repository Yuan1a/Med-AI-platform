package com.graphy.platform.controller;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformRequestPageParam;

import com.graphy.db.entity.TbPlatformRequestEntity;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformRequestService;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther lsd
 * @create 2021-08-07 10:44:52
 * @describe 网络请求监听
 */
@Data
@Controller("com.graphy.platform.controller.platformrequestcontroller")
@RequiredArgsConstructor
@Api(tags = "网络请求监听")
@RequestMapping("/pf/PlatformRequest")
@Slf4j
public class PlatformRequestController {

    private final PlatformRequestService platformRequestService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "首页", notes = "首页")
    private String index() throws Exception {
        return "/platform/request/index";
    }

    /**
     * 详情页面
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "详情页面", notes = "详情页面")
    private String info(String id, Model model) throws Exception {
        TbPlatformRequestEntity orm = platformRequestService.getById(id);
        model.addAttribute("orm", orm);
        return "/platform/request/info";
    }

    /**
    * auther： lsd
    * create： 2021-08-07 23:11:13
    * describe： 分页获取数据
    */
    @RequestMapping(method = RequestMethod.POST, value = "/getPlatformRequestPage")
    @ApiOperation(value="分页获取数据", notes="分页获取数据")
    @ResponseBody
    private OwnResult<OwnPageResult<TbPlatformRequestEntity>> getPlatformRequestPage(@RequestBody PlatformRequestPageParam param) throws Exception {
        return platformRequestService.getPlatformRequestPage(param);
    }
}