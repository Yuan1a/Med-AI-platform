package com.graphy.unit.wechat;

/**
 * 微信api请求返回结果
 *
 * @param <T>
 */
@SuppressWarnings("all")
public class WxResult<T> {
   /**
    * 结果编码
    */
   public String code;
   /**
    * 微信返回的编码
    */
   public String wxCode;
   /**
    * 错误信息
    */
   public String error;
   /**
    * 返回结果
    */
   public T result;

   /**
    * 错误信息
    *
    * @param error
    * @param wxCode
    */
   public static <T> WxResult error(String error, String wxCode) {
      WxResult<T> res = new WxResult<T>();
      res.code = "0";
      res.wxCode = wxCode;
      res.error = error;
      return res;
   }

   /**
    * 错误信息
    *
    * @param error
    */
   public static <T> WxResult error(String error) {
      WxResult<T> res = new WxResult<T>();
      res.code = "0";
      res.error = error;
      return res;
   }

   /**
    * 设置返回值的对象
    *
    * @param data
    */
   public static <T> WxResult result(T data) {
      WxResult<T> res = new WxResult<T>();
      res.code = "1";
      res.result = data;
      return res;
   }

   public String getWxCode() {
      return wxCode;
   }

   public void setWxCode(String wxCode) {
      this.wxCode = wxCode;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }

   public T getResult() {
      return result;
   }

   public void setResult(T result) {
      this.result = result;
   }
}
