package com.graphy.unit.wechat.attr;

/**
 * 小程序支付参数
 */
public class PayMiniConfigBeforeSp {
   /**
    * SUCCESS/FAIL
    * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
    */
   public String return_code;
   /**
    * SUCCESS/FAIL
    */
   public String result_code;
   /**
    * 当return_code为FAIL时返回信息为错误原因 ，例如
    * <p>
    * 签名失败
    * <p>
    * 参数格式校验错误
    */
   public String return_msg;
   /**
    * 调用接口提交的公众账号ID
    */
   public String appid;
   /**
    * 调用接口提交的商户号
    */
   public String mch_id;
   /**
    * 微信返回的随机字符串
    */
   public String nonce_str;
   /**
    * 微信返回的签名值
    */
   public String sign;
   /**
    * 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
    */
   public String prepay_id;
   /**
    * JSAPI -JSAPI支付
    * <p>
    * NATIVE -Native支付
    * <p>
    * APP -APP支付
    */
   public String trade_type;

   /**
    * 二维码链接图片
    */
   public String code_url;

   public String getCode_url() {
      return code_url;
   }

   public void setCode_url(String code_url) {
      this.code_url = code_url;
   }

   public String getReturn_code() {
      return return_code;
   }

   public void setReturn_code(String return_code) {
      this.return_code = return_code;
   }

   public String getReturn_msg() {
      return return_msg;
   }

   public void setReturn_msg(String return_msg) {
      this.return_msg = return_msg;
   }

   public String getAppid() {
      return appid;
   }

   public void setAppid(String appid) {
      this.appid = appid;
   }

   public String getMch_id() {
      return mch_id;
   }

   public void setMch_id(String mch_id) {
      this.mch_id = mch_id;
   }

   public String getNonce_str() {
      return nonce_str;
   }

   public void setNonce_str(String nonce_str) {
      this.nonce_str = nonce_str;
   }

   public String getSign() {
      return sign;
   }

   public void setSign(String sign) {
      this.sign = sign;
   }

   public String getResult_code() {
      return result_code;
   }

   public void setResult_code(String result_code) {
      this.result_code = result_code;
   }

   public String getPrepay_id() {
      return prepay_id;
   }

   public void setPrepay_id(String prepay_id) {
      this.prepay_id = prepay_id;
   }

   public String getTrade_type() {
      return trade_type;
   }

   public void setTrade_type(String trade_type) {
      this.trade_type = trade_type;
   }
}
