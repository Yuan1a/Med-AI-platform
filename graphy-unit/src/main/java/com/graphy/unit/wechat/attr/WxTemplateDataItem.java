package com.graphy.unit.wechat.attr;

/**
 * 模板值配置
 */
public class WxTemplateDataItem {
   /**
    * 值
    */
   private String value;
   /**
    * 颜色
    */
   private String color;

   public WxTemplateDataItem(String value) {
      this.setValue(value);
      this.setColor("#000");
   }

   public WxTemplateDataItem(String value, String color) {
      this.setValue(value);
      this.setColor(color);
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getColor() {
      return color;
   }

   public void setColor(String color) {
      this.color = color;
   }
}
