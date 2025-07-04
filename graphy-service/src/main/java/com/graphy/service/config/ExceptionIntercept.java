package com.graphy.service.config;

import com.graphy.service.bean.OwnResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获
 */
@ControllerAdvice("com.graphy")
@Slf4j
public class ExceptionIntercept {
    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public OwnResult<Object> errorResponseBody(Exception ex) {
        log.error("错误日志:", ex);
        System.out.println(ex);
        return OwnResult.error(ex.toString());
    }

}
