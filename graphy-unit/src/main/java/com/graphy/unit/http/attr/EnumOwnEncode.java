package com.graphy.unit.http.attr;

/**
 * 请求数据返回编码
 */
public enum EnumOwnEncode {
   /**
    * utf-8
    */
   utf_8("utf-8"),
   /**
    * GBK
    */
   gbk("GBK"),
   /**
    * ISO-8859-1
    */
   iso_8859_1("ISO-8859-1");
   private String value;

   EnumOwnEncode(String value) {
      this.value = value;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

}
