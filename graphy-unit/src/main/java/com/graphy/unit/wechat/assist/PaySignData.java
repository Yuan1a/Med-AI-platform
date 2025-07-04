package com.graphy.unit.wechat.assist;

import java.util.*;

/**
 * 支付参数签名管理
 */
public class PaySignData {
   /**
    * 小程序/公众号 appId
    */
   private String appId;
   /**
    * 商户号ID
    */
   private String mchId;
   /**
    * 商户号密匙
    */
   private String mchKey;
   /**
    * 参数
    */
   private List<PaySignDataItem> dataItem;

   /**
    * 构造函数
    *
    * @param appId  小程序/公众号 appId
    * @param mchId  商户号ID
    * @param mchKey 商户号密匙
    */
   public PaySignData(String appId, String mchId, String mchKey) {
      this.appId = appId;
      this.mchId = mchId;
      this.mchKey = mchKey;
      dataItem = new ArrayList<PaySignDataItem>();
   }

   /**
    * 添加参数
    *
    * @param dataItem
    */
   public void addDataItem(PaySignDataItem dataItem) {
      this.dataItem.add(dataItem);
   }

   /**
    * 获取签名后的xml
    *
    * @return
    */
   public String getSignXml() {
      Map<String, PaySignDataItem> params = new HashMap<String, PaySignDataItem>();
      params.put("appid", new PaySignDataItem("appid", this.appId, null));
      params.put("mch_id", new PaySignDataItem("mch_id", this.mchId, null));
      params.put("nonce_str", new PaySignDataItem("nonce_str", UUID.randomUUID().toString().replaceAll("-", ""), null));
      for (PaySignDataItem item : this.dataItem) {
         if (!params.containsKey(item.name)) {
            params.put(item.name, item);
         }
      }
      List<Map.Entry<String, PaySignDataItem>> infoIds = new ArrayList<Map.Entry<String, PaySignDataItem>>(params.entrySet());
      // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
      Collections.sort(infoIds, new Comparator<Map.Entry<String, PaySignDataItem>>() {
         public int compare(Map.Entry<String, PaySignDataItem> o1, Map.Entry<String, PaySignDataItem> o2) {
            return (o1.getKey()).toString().compareTo(o2.getKey());
         }
      });
      StringBuffer sb = new StringBuffer();
      for (Map.Entry<String, PaySignDataItem> item : infoIds) {
         PaySignDataItem dataItem = item.getValue();
         if (!"sign".equals(dataItem.name) && !"key".equals(dataItem.name)) {
            String keyValue = dataItem.name + "=" + dataItem.value;
            sb.append(sb.toString().equals("") ? keyValue : "&" + keyValue);
         }
      }
      sb.append("&key=" + this.mchKey);
      String sign = WxApiCommon.MD5(sb.toString()).toUpperCase();
      StringBuffer sbXml = new StringBuffer();
      sbXml.append("<xml>");
      for (Map.Entry<String, PaySignDataItem> item : infoIds) {
         PaySignDataItem dataItem = item.getValue();
         if (!"sign".equals(dataItem.name) && !"key".equals(dataItem.name)) {
            if (dataItem.dataItemEnum == PaySignDataItemEnum.String) {
               sbXml.append("<" + dataItem.name + ">").append(dataItem.value).append("</" + dataItem.name + ">");
            } else if (dataItem.dataItemEnum == PaySignDataItemEnum.CDATA) {
               sbXml.append("<" + dataItem.name + "><![CDATA[").append(dataItem.value).append("]]></" + dataItem.name + ">");
            }
         }
      }
      sbXml.append("<sign>").append(sign).append("</sign>");
      sbXml.append("</xml>");
      return sbXml.toString();
   }

}
