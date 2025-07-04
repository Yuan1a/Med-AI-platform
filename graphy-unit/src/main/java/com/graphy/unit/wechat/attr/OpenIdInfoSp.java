package com.graphy.unit.wechat.attr;

/**
 * 获取微信OpenId属性
 */
public class OpenIdInfoSp {
   public String session_key;
   public String expires_in;
   /**
    * 微信唯一标识
    */
   public String unionId;
   /**
    * 微信openId
    */
   public String openid;

   public String getSession_key() {
      return session_key;
   }

   public void setSession_key(String session_key) {
      this.session_key = session_key;
   }

   public String getExpires_in() {
      return expires_in;
   }

   public void setExpires_in(String expires_in) {
      this.expires_in = expires_in;
   }

   public String getUnionId() {
      return unionId;
   }

   public void setUnionId(String unionId) {
      this.unionId = unionId;
   }

   public String getOpenid() {
      return openid;
   }

   public void setOpenid(String openid) {
      this.openid = openid;
   }
}
