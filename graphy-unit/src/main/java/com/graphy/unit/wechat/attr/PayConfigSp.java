package com.graphy.unit.wechat.attr;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 调起支付API的参数
 */
public class PayConfigSp {
   /**
    * 时间戳从1970年1月1日00:00:00至今的秒数,即当前的时间
    */
   public String timeStamp;
   /**
    * 随机字符串，不长于32位。
    */
   public String nonceStr;
   /**
    * 统一下单接口返回的 prepay_id 参数值，提交格式如：prepay_id=wx2017033010242291fcfe0db70013231072
    */
   @JSONField(name = "package")
   public String packageValue;
   /**
    * 签名类型，默认为MD5，支持HMAC-SHA256和MD5。注意此处需与统一下单的签名类型一致
    */
   public String signType;
   /**
    * 支付签名
    */
   public String paySign;


   public String getTimeStamp() {
      return timeStamp;
   }

   public void setTimeStamp(String timeStamp) {
      this.timeStamp = timeStamp;
   }

   public String getNonceStr() {
      return nonceStr;
   }

   public void setNonceStr(String nonceStr) {
      this.nonceStr = nonceStr;
   }

   public String getPackageValue() {
      return packageValue;
   }

   public void setPackageValue(String packageValue) {
      this.packageValue = packageValue;
   }

   public String getSignType() {
      return signType;
   }

   public void setSignType(String signType) {
      this.signType = signType;
   }

   public String getPaySign() {
      return paySign;
   }

   public void setPaySign(String paySign) {
      this.paySign = paySign;
   }
}
