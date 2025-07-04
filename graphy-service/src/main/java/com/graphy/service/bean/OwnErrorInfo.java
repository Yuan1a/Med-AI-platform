package com.graphy.service.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 异常对象
 */
@Data
@AllArgsConstructor
public class OwnErrorInfo {
    /**
     * 异常类别 1=会话过期 2=无权限访问
     */
    private String errorType;
    /**
     * 异常信息
     */
    private String errorInfo;
}
