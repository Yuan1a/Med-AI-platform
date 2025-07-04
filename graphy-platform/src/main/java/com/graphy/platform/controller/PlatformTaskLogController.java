package com.graphy.platform.controller;
import com.graphy.service.bean.dto.PlatformTaskLogDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformTaskLogParam;

import lombok.Data;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.graphy.service.service.PlatformTaskLogService;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther lsd
 * @create 2021-08-08 23:26:38
 * @describe 任务日志
 */
@Data
@Controller("com.graphy.platform.controller.platformtasklogcontroller")
@RequiredArgsConstructor
@Api(tags = "任务日志")
@RequestMapping("/pf/PlatformTaskLog")
@Slf4j
public class PlatformTaskLogController {

    private final PlatformTaskLogService platformTaskLogService;

    /**
    * auther： lsd
    * create： 2021-08-09 22:40:25
    * describe： 分页获取任务执行日志
    */
    @RequestMapping(method = RequestMethod.POST, value = "/getPlatformTaskLog")
    @ApiOperation(value="分页获取任务执行日志", notes="分页获取任务执行日志")
    @ResponseBody
    private OwnResult<OwnPageResult<PlatformTaskLogDto>> getPlatformTaskLog(@RequestBody PlatformTaskLogParam param) throws Exception {
        return platformTaskLogService.getPlatformTaskLog(param);
    }
}