package com.graphy.unit.wechat.attr;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送模板对象
 */
public class WxTemplateQu {

   /**
    * 接收人OpenId
    */
   private String touser;
   /**
    * 模板ID
    */
   private String template_id;
   /**
    * 点击链接地址
    */
   private String url;
   /**
    * 小程序跳转配置
    */
   private WxTemplateMiniProgram miniprogram;
   /**
    * 模板数据
    */
   private Map<String, WxTemplateDataItem> data;

   /**
    * 构造函数
    *
    * @param touser      openId
    * @param template_id 模板ID
    */
   public WxTemplateQu(String touser, String template_id) {
      this.setTouser(touser);
      this.setTemplate_id(template_id);
   }

   public void addDataItem(String name, WxTemplateDataItem wtdi) {
      if (this.getData() == null) this.data = new HashMap<String, WxTemplateDataItem>();
      this.data.put(name, wtdi);
   }


   public String getTouser() {
      return touser;
   }

   public void setTouser(String touser) {
      this.touser = touser;
   }

   public String getTemplate_id() {
      return template_id;
   }

   public void setTemplate_id(String template_id) {
      this.template_id = template_id;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public WxTemplateMiniProgram getMiniprogram() {
      return miniprogram;
   }

   public void setMiniprogram(WxTemplateMiniProgram miniprogram) {
      this.miniprogram = miniprogram;
   }

   public Map<String, WxTemplateDataItem> getData() {
      return data;
   }

   public void setData(Map<String, WxTemplateDataItem> data) {
      this.data = data;
   }

}
