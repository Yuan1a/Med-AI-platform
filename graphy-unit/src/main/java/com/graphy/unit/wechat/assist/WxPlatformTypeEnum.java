package com.graphy.unit.wechat.assist;

/**
 * 目标平台类别
 */
public enum WxPlatformTypeEnum {
   /**
    * 微信小程序
    */
   MiniProgram("1", "微信小程序"),
   /**
    * 公众号
    */
   OfficialAccounts("2", "公众号");

   private WxPlatformTypeEnum(String value, String name) {
      this.value = value;
      this.name = name;
   }


   private String value;
   private String name;

   /**
    * 获取值
    *
    * @return
    */
   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   /**
    * 获取名称
    *
    * @return
    */
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
