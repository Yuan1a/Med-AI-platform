package com.graphy.unit.wechat.attr;

/**
 * 小程序跳转
 */
public class WxTemplateMiniProgram {
   /**
    * 跳转小程序的appid
    */
   private String appid;
   /**
    * 跳转小程序的路径页面
    */
   private String pagepath;

   /**
    * 构造函数
    *
    * @param appid    跳转小程序的appid
    * @param pagepath 跳转小程序的路径页面
    */
   public WxTemplateMiniProgram(String appid, String pagepath) {
      this.setAppid(appid);
      this.setPagepath(pagepath);
   }

   public String getAppid() {
      return appid;
   }

   public void setAppid(String appid) {
      this.appid = appid;
   }

   public String getPagepath() {
      return pagepath;
   }

   public void setPagepath(String pagepath) {
      this.pagepath = pagepath;
   }
}
