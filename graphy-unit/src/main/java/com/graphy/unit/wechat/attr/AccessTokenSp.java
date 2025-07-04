package com.graphy.unit.wechat.attr;

/**
 * 微信凭证
 */
public class AccessTokenSp {
   /**
    * 微信凭证
    */
   public String access_token;
   /**
    * 有效时间
    */
   public String expires_in;

   public String getAccess_token() {
      return access_token;
   }

   public void setAccess_token(String access_token) {
      this.access_token = access_token;
   }

   public String getExpires_in() {
      return expires_in;
   }

   public void setExpires_in(String expires_in) {
      this.expires_in = expires_in;
   }
}
