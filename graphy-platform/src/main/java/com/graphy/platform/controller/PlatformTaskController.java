package com.graphy.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.graphy.db.entity.TbPlatformTaskEntity;
import com.graphy.db.entity.TbPlatformTaskLogEntity;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.dto.PlatformTaskLogDto;
import com.graphy.service.bean.param.PlatformTaskLogParam;
import com.graphy.service.bean.param.PlatformTaskPageParam;
import com.graphy.service.service.PlatformTaskLogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformTaskService;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther lsd
 * @create 2021-08-08 11:37:35
 * @describe 定时任务
 */
@Data
@Controller("com.graphy.platform.controller.platformtaskcontroller")
@RequiredArgsConstructor
@Api(tags = "定时任务")
@RequestMapping("/pf/PlatformTask")
@Slf4j
public class PlatformTaskController {

    private final PlatformTaskService platformTaskService;
    private final PlatformTaskLogService platformTaskLogService;


    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "首页", notes = "首页")
    private String index() throws Exception {
        return "/platform/task/index";
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
        TbPlatformTaskEntity orm = new TbPlatformTaskEntity();
        if (!StrUtil.hasEmpty(id)) {
            model.addAttribute("orm", platformTaskService.getById(id));
        } else {
            orm.setIndex(0);
            orm.setRunStatus("run");
            orm.setLogTimeOut(-1);
            model.addAttribute("orm", orm);
        }
        return "/platform/task/edit";
    }


    /**
     * auther： lsd
     * create： 2021-08-08 11:40:46
     * describe： 分页获取任务记录
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getPlatformTaskPage")
    @ApiOperation(value = "分页获取任务记录", notes = "分页获取任务记录")
    @ResponseBody
    private OwnResult<OwnPageResult<TbPlatformTaskEntity>> getPlatformTaskPage(@RequestBody PlatformTaskPageParam param) throws Exception {
        return platformTaskService.getPlatformTaskPage(param);
    }

    /**
     * auther： lsd
     * create： 2021-08-08 11:43:19
     * describe： 保存定时任务
     */
    @RequestMapping(method = RequestMethod.POST, value = "/savePlatformTask")
    @ApiOperation(value = "保存定时任务", notes = "保存定时任务")
    @ResponseBody
    private OwnResult<Boolean> savePlatformTask(@RequestBody TbPlatformTaskEntity param) throws Exception {
        return platformTaskService.savePlatformTask(param);
    }

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 删除机构
     */
    @RequestMapping(method = RequestMethod.POST, value = "/del")
    @ApiOperation(value = "删除任务", notes = "删除任务")
    @ApiImplicitParams({@ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String")})
    @ResponseBody
    private OwnResult<Boolean> del(String id) throws Exception {
        TbPlatformTaskEntity entity = new TbPlatformTaskEntity();
        entity.setStatus("0");
        boolean ok = platformTaskService.update(entity, new LambdaUpdateWrapper<TbPlatformTaskEntity>().eq(TbPlatformTaskEntity::getId, id));
        return OwnResult.result(ok);
    }

    /**
     * auther： lsd
     * create： 2021-07-23 20:28:05
     * describe： 设置运行状态
     */
    @RequestMapping(method = RequestMethod.POST, value = "/setRunStatus")
    @ApiOperation(value = "设置运行状态", notes = "设置运行状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "运行状态 run=运行中 pause=暂停", value = "runStatus", required = true, dataType = "String")
    })
    @ResponseBody
    private OwnResult<Boolean> setRunStatus(String id, String runStatus) throws Exception {
        TbPlatformTaskEntity entity = new TbPlatformTaskEntity();
        entity.setRunStatus(runStatus);
        boolean ok = platformTaskService.update(entity, new LambdaUpdateWrapper<TbPlatformTaskEntity>().eq(TbPlatformTaskEntity::getId, id));
        return OwnResult.result(ok);
    }


    /**
     * auther： lsd
     * create： 2021-08-09 22:40:25
     * describe： 分页获取任务执行日志
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getPlatformTaskLog")
    @ApiOperation(value = "分页获取任务执行日志", notes = "分页获取任务执行日志")
    @ResponseBody
    private OwnResult<OwnPageResult<PlatformTaskLogDto>> getPlatformTaskLog(@RequestBody PlatformTaskLogParam param) throws Exception {
        return platformTaskLogService.getPlatformTaskLog(param);
    }

    /**
     * 任务记录详情页面
     *
     * @param id    主键
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/taskLogInfo", method = RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "主键", value = "id", required = true, dataType = "String")})
    @ApiOperation(value = "任务记录详情页面", notes = "任务记录详情页面")
    public String taskLogInfo(@RequestParam(value = "id", required = false) String id, Model model) throws Exception {
        TbPlatformTaskLogEntity orm = platformTaskLogService.getById(id);
        model.addAttribute("orm", orm);
        return "/platform/task/taskloginfo";
    }
}