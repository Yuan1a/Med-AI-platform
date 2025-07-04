package com.graphy.unit.sms.attr;

/**
 * 短信发送结果
 */
public class SmsResult {
   /**
    * 1=正常 0=异常
    */
   public String code;
   /**
    * 错误信息
    */
   public String error;


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

   /**
    * 返回成功
    *
    * @return
    */
   public static SmsResult ok() {
      SmsResult res = new SmsResult();
      res.setCode("1");
      return res;
   }

   /**
    * 返回错误信息
    *
    * @param error 错误信息
    * @return
    */
   public static SmsResult error(String error) {
      SmsResult res = new SmsResult();
      res.setCode("0");
      res.setError(error);
      return res;
   }
}
