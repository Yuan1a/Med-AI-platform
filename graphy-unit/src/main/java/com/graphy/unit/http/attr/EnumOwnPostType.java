package com.graphy.unit.http.attr;

/**
 * 参数类型
 */
public enum EnumOwnPostType {
   /**
    * 对象
    */
   object("对象", "application/json"),
   /**
    * 表单
    */
   form("表单", "application/x-www-form-urlencoded");

   private String name;
   private String value;

   EnumOwnPostType(String name, String value) {
      this.name = name;
      this.value = value;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }
}
