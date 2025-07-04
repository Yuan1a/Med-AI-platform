package com.graphy.unit.wechat.attr;

/**
 * 消息模板推送结果
 */
public class WxTemplateSp {
   /**
    * 错误编码 0=正常
    */
   public Integer errcode;
   /**
    * 错误信息
    */
   public String errmsg;
   /**
    * 模板推送成功后返回的ID
    */
   public Long msgid;

   public Integer getErrcode() {
      return errcode;
   }

   public void setErrcode(Integer errcode) {
      this.errcode = errcode;
   }

   public String getErrmsg() {
      return errmsg;
   }

   public void setErrmsg(String errmsg) {
      this.errmsg = errmsg;
   }

   public Long getMsgid() {
      return msgid;
   }

   public void setMsgid(Long msgid) {
      this.msgid = msgid;
   }
}
