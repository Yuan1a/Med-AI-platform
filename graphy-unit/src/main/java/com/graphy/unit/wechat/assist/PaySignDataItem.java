package com.graphy.unit.wechat.assist;

/**
 * 参数
 */
public class PaySignDataItem {
   /**
    * 参数名称
    */
   public String name;
   /**
    * 参数值
    */
   public String value;
   /**
    * 参数类型
    */
   public PaySignDataItemEnum dataItemEnum;

   /**
    * 构造函数
    *
    * @param name         参数名称
    * @param value        参数值
    * @param dataItemEnum 参数类型
    */
   public PaySignDataItem(String name, String value, PaySignDataItemEnum dataItemEnum) {
      if (dataItemEnum == null) dataItemEnum = PaySignDataItemEnum.String;
      this.name = name;
      this.value = value;
      this.dataItemEnum = dataItemEnum;
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

   public PaySignDataItemEnum getDataItemEnum() {
      return dataItemEnum;
   }

   public void setDataItemEnum(PaySignDataItemEnum dataItemEnum) {
      this.dataItemEnum = dataItemEnum;
   }
}
