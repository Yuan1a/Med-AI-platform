package com.graphy.service.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 响应对象
 *
 * @param <T>
 */
@ApiModel(description = "响应对象")
public class OwnResult<T> {
    /**
     * 结果状态码：
     * 1：成功
     * 0: 失败
     */
    @ApiModelProperty(value = "1=成功 0=失败", example = "1", position = 1006)
    public String code;
    /**
     * 返回信息
     */
    @ApiModelProperty(value = "响应消息", position = 1007)
    public String msg;
    /**
     * 返回结果
     */
    @ApiModelProperty(value = "响应结果", position = 1008)
    public T result;
    /**
     * 响应其他值
     */
    @ApiModelProperty(value = "响应其他值", position = 1009)
    public Object other;

    /**
     * 设置错误信息
     *
     * @param error
     */
    public static <T> OwnResult error(String error) {
        OwnResult<T> res = new OwnResult<T>();
        res.code = "0";
        res.msg = error;
        return res;
    }

    /**
     * 设置消息的对象
     *
     * @param msg
     */
    public static <T> OwnResult msg(String msg) {
        OwnResult<T> res = new OwnResult<T>();
        res.code = "1";
        res.msg = msg;
        return res;
    }


    /**
     * 获取结果对象
     *
     * @param msg
     */
    public static <T> OwnResult msg(String code, String msg) {
        OwnResult<T> res = new OwnResult<T>();
        res.code = code;
        res.msg = msg;
        return res;
    }

    /**
     * 设置返回值的对象
     *
     * @param data
     */
    public static <T> OwnResult result(T data) {
        OwnResult<T> res = new OwnResult<T>();
        res.code = "1";
        res.result = data;
        return res;
    }

    /**
     * 未登录
     */
    public static <T> OwnResult notLogin() {
        OwnResult<T> res = new OwnResult<T>();
        res.code = "1000";
        res.msg = "未登录";
        return res;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }
}
