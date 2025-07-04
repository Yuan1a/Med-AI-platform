package com.graphy.service.config;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.graphy.config.BaseConfig;
import com.graphy.db.entity.*;
import com.graphy.service.bean.OwnUserInfo;
import com.graphy.service.service.PlatformCommonService;
import com.graphy.service.service.PlatformDbUpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@Component

public class MybatisPlusUpdateInterceptor implements InnerInterceptor {


    /**
     * 获取参数中的实体对象
     *
     * @param parameter
     * @return
     */
    private Object getEntity(Object parameter) {
        if (parameter != null) {
            if (parameter instanceof Map) {
                Map<?, ?> parameterMap = (Map) parameter;
                Iterator var2 = parameterMap.entrySet().iterator();
                while (var2.hasNext()) {
                    Map.Entry entry = (Map.Entry) var2.next();
                    Object obj = entry.getValue();
                    if (obj.getClass().getAnnotation(TableName.class) != null) {
                        return obj;
                    }
                }
            } else {
                if (parameter.getClass().getAnnotation(TableName.class) != null) {
                    return parameter;
                }
            }

        }
        return null;
    }

    /**
     * 设置对象属性值
     *
     * @param entity
     * @param fieldName
     * @param value
     */
    private void setEntityValue(Object entity, String fieldName, Object value) {
        try {
            Field field = entity.getClass().getDeclaredField(fieldName);

            if (field != null) {
                field.setAccessible(true);
                Object svalue = field.get(entity);
                if (svalue == null) {
                    field.set(entity, value);
                }
            }
        } catch (Exception err) {

        }
    }

    /**
     * 获取对象属性值
     *
     * @param entity
     * @param fieldName
     */
    private Object getEntityValue(Object entity, String fieldName) {
        try {
            Field field = entity.getClass().getDeclaredField(fieldName);
            if (field != null) {
                field.setAccessible(true);
                return field.get(entity);
            }
        } catch (Exception err) {

        }
        return null;
    }

    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        try {
            Object entity = getEntity(parameter);
            if (entity != null) {
                String handleStatus = "";
                PlatformCommonService platformCommonService = SpringContextUtil.getBean(PlatformCommonService.class);
                TbPlatformConfigEntity configEntity = platformCommonService.getPlatformConfig();
                OwnUserInfo userInfo = platformCommonService.getUserInfo();
                if (ms.getSqlCommandType().equals(SqlCommandType.INSERT)) {
                    handleStatus = "1";
                    setEntityValue(entity, BaseConfig.STATUS_NAME, "1");
                    setEntityValue(entity, BaseConfig.CUSER_NAME, userInfo.getUserId());
                    setEntityValue(entity, BaseConfig.CUSER_NAME_NAME, userInfo.getUserName());
                    setEntityValue(entity, BaseConfig.CTIME_NAME, new Date());
                } else if (ms.getSqlCommandType().equals(SqlCommandType.UPDATE)) {
                    Object statusValue = getEntityValue(entity, BaseConfig.STATUS_NAME);
                    if (statusValue == null || statusValue.toString().equals("1")) {
                        handleStatus = "2";
                        setEntityValue(entity, BaseConfig.UUSER_NAME, userInfo.getUserId());
                        setEntityValue(entity, BaseConfig.UUSER_NAME_NAME, userInfo.getUserName());
                        setEntityValue(entity, BaseConfig.UTIME_NAME, new Date());
                    } else if (statusValue.toString().equals("0")) {
                        handleStatus = "3";
                        setEntityValue(entity, BaseConfig.DUSER_NAME, userInfo.getUserId());
                        setEntityValue(entity, BaseConfig.DUSER_NAME_NAME, userInfo.getUserName());
                        setEntityValue(entity, BaseConfig.DTIME_NAME, new Date());
                    }
                } else if (ms.getSqlCommandType().equals(SqlCommandType.DELETE)) {
                    handleStatus = "4";
                }

                try {

                    if (!StrUtil.hasEmpty(configEntity.getDbUpLogOpen())
                            && configEntity.getDbUpLogOpen().equals("1")
                            && !entity.getClass().equals(TbPlatformDbUpEntity.class)
                            && !entity.getClass().equals(TbPlatformLoginEntity.class)
                            && !entity.getClass().equals(TbPlatformLoginErrorEntity.class)
                            && !entity.getClass().equals(TbPlatformRequestEntity.class)
                            && !entity.getClass().equals(TbPlatformTaskEntity.class)
                            && !entity.getClass().equals(TbPlatformTaskLogEntity.class)
                    ) {
                        TbPlatformDbUpEntity dbUpEntity = new TbPlatformDbUpEntity();
                        dbUpEntity.setTableName(entity.getClass().getAnnotation(TableName.class).value());
                        dbUpEntity.setUpData(JSONObject.toJSONString(entity));
                        dbUpEntity.setUrl(userInfo.getRequestUrl());
                        dbUpEntity.setIp(userInfo.getIp());
                        dbUpEntity.setHandleStatus(handleStatus);
                        dbUpEntity.setStatus("1");
                        dbUpEntity.setCtime(new Date());
                        dbUpEntity.setCuser(userInfo.getUserId());
                        dbUpEntity.setCuserName(userInfo.getUserName());
                        dbUpEntity.setRecordTimeOut(com.graphy.unit.date.Api.dateAddDate(new Date(), configEntity.getDbUpLogKeepTime()));
                        PlatformDbUpService platformDbUpService = SpringContextUtil.getBean(PlatformDbUpService.class);
                        platformDbUpService.save(dbUpEntity);
                    }
                } catch (Exception err) {
                    err.printStackTrace();
                    log.error(err.toString());
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
            log.error(err.toString());
        }


    }


}
