package com.graphy.unit.wechat.attr;

/**
 * 小程序支付成功后返回的支付结果
 */
public class PayResultSp {
   private String returnCode;
   private String openId;
   private String outTradeNo;
   private String transactionId;

   public String getReturnCode() {
      return returnCode;
   }

   public void setReturnCode(String returnCode) {
      this.returnCode = returnCode;
   }

   public String getOpenId() {
      return openId;
   }

   public void setOpenId(String openId) {
      this.openId = openId;
   }

   public String getOutTradeNo() {
      return outTradeNo;
   }

   public void setOutTradeNo(String outTradeNo) {
      this.outTradeNo = outTradeNo;
   }

   public String getTransactionId() {
      return transactionId;
   }

   public void setTransactionId(String transactionId) {
      this.transactionId = transactionId;
   }
}
