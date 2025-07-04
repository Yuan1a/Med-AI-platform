package com.graphy.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.graphy.db.entity.TbPlatformTaskEntity;
import com.graphy.db.entity.TbPlatformTaskLogEntity;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PlatformTaskPageParam;
import com.graphy.service.config.SpringContextUtil;
import com.graphy.service.service.PlatformTaskLogService;
import com.graphy.service.service.PlatformTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Profile({"pro", "test"})
@Configuration("com.graphy.task.quartztaskstart")
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
/**
 * 定时任务调度主程序
 */
public class QuartzTaskStart implements SchedulingConfigurer {

    private final PlatformTaskService platformTaskService;
    private final PlatformTaskLogService platformTaskLogService;
    /**
     * 任务执行状态
     */
    private static Map<String, Boolean> runStatus;


    private void printMsg(String msg) {
        //log.info(msg);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        try {
            runStatus = new HashMap<>();
            PlatformTaskPageParam platformTaskPageParam = new PlatformTaskPageParam();
            platformTaskPageParam.setRunStatus("run");
            platformTaskPageParam.setSize(5000L);
            platformTaskPageParam.setPage(1L);
            OwnResult<OwnPageResult<TbPlatformTaskEntity>> resultOwnResult = platformTaskService.getPlatformTaskPage(platformTaskPageParam);
            List<TbPlatformTaskEntity> tasks = resultOwnResult.getResult().getRecords();
            for (TbPlatformTaskEntity item : tasks) {
                runStatus.put(item.getBeanName() + "." + item.getFunName(), false);
                taskRegistrar.addTriggerTask(
                        () -> {
                        },
                        triggerContext -> {
                            TbPlatformTaskLogEntity taskLogEntity = null;
                            if (item.getLogTimeOut() != null && item.getLogTimeOut() >= 0) {
                                taskLogEntity = new TbPlatformTaskLogEntity();
                                taskLogEntity.setTaskId(item.getId());
                                taskLogEntity.setRunStartTime(new Date());
                            }
                            String key = item.getBeanName() + "." + item.getFunName();
                            String taskName = item.getTaskName();
                            String start = taskName + "任务(开始): " + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                            printMsg(start);
                            String cron = item.getCron();
                            Object result = null;
                            String errorMsg = null;
                            try {
                                if (CronExpression.isValidExpression(cron)) {
                                    if (!runStatus.get(key)) {
                                        runStatus.put(key, true);
                                        Object bean = SpringContextUtil.getBean(item.getBeanName());
                                        if (bean == null) throw new Exception("【" + taskName + "】无法找到bean(" + item.getBeanName() + ")");
                                        Method method = bean.getClass().getDeclaredMethod(item.getFunName());
                                        if (method == null) throw new Exception("【" + taskName + "】无法找到bean函数(" + item.getBeanName() + "->" + item.getFunName() + ")");
                                        result = method.invoke(bean);
                                        String msg = taskName + "任务(截止): " + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                                        printMsg(msg);
                                    } else {
                                        errorMsg = "【" + taskName + "】上一次任务还没有执行完,当前任务调度略过";
                                        printMsg(errorMsg);
                                    }
                                } else {
                                    errorMsg = "【" + taskName + "】任务cron格式错误";
                                    printMsg(errorMsg);
                                }
                            } catch (Exception err) {
                                errorMsg = err.toString();
                                printMsg(errorMsg);
                            } finally {
                                try {
                                    if (taskLogEntity != null) {
                                        Integer logTimeOut = item.getLogTimeOut();
                                        if (logTimeOut.equals(0)) {
                                            taskLogEntity.setLogTimeOut(DateUtil.parse("2121-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
                                        } else {
                                            taskLogEntity.setLogTimeOut(com.graphy.unit.date.Api.dateAddDate(taskLogEntity.getRunStartTime(), logTimeOut));
                                        }
                                        taskLogEntity.setRunEndTime(new Date());
                                        taskLogEntity.setHappenError(StrUtil.hasEmpty(errorMsg) ? "0" : "1");
                                        if (!StrUtil.hasEmpty(errorMsg)) taskLogEntity.setError(errorMsg);
                                        if (result != null) taskLogEntity.setResult(JSONObject.toJSONString(result));
                                        platformTaskLogService.save(taskLogEntity);
                                    }
                                } catch (Exception err) {
                                    err.printStackTrace();
                                }
                                runStatus.put(key, false);
                                return new CronTrigger(cron).nextExecutionTime(triggerContext);
                            }
                        }
                );
            }
        } catch (Exception err) {
            String msg = err.toString();
            printMsg(msg);
        }
    }
}
