package com.graphy.unit.wechat.assist;

import java.security.MessageDigest;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;

/**
 * 微信公共使用
 */
public class WxApiCommon {

   /**
    * xml文档Document转对象
    *
    * @param document
    * @param clazz
    * @return
    */
   public static Object getObject(Document document, Class<?> clazz) {
      Object obj = null;
      //获取根节点
      Element root = document.getRootElement();
      try {
         obj = clazz.newInstance();//创建对象
         Field[] fields = obj.getClass().getDeclaredFields();
         for (Field item : fields) {
            item.setAccessible(true);
            Element element = root.element(item.getName());
            if (element != null) {
               String typeName = item.getType().getTypeName();
               if (typeName.equals("java.lang.String")) {
                  item.set(obj, element.getText());
               } else if (typeName.equals("java.lang.Integer")) {
                  item.set(obj, Integer.valueOf(element.getText()));
               } else if (typeName.equals("java.util.Date")) {
                  Date date = com.graphy.unit.date.Api.dateFormat(element.getText(), "yyyyMMddHHmmss");
                  item.set(obj, date);
               }

            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return obj;
   }

   /**
    * xml字符串转对象
    *
    * @param xmlString
    * @param clazz
    * @return
    */
   public static Object getObject(String xmlString, Class<?> clazz) throws Exception {
      Document document = null;
      try {
         document = DocumentHelper.parseText(xmlString);
      } catch (Exception e) {
         throw new Exception("获取Document异常" + xmlString);
      }      //获取根节点
      return getObject(document, clazz);
   }


   public static String getMessageDigest(byte[] buffer) {
      char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
      try {
         MessageDigest mdTemp = MessageDigest.getInstance("MD5");
         mdTemp.update(buffer);
         byte[] md = mdTemp.digest();
         int j = md.length;
         char str[] = new char[j * 2];
         int k = 0;
         for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
         }
         return new String(str);
      } catch (Exception e) {
         return null;
      }
   }


   /**
    * MD5加密
    *
    * @param str
    * @return
    */
   public static String MD5(String str) {
      String smd5 = null;
      try {
         byte[] bytes = str.getBytes("UTF-8");
         MessageDigest md5 = MessageDigest.getInstance("MD5");
         md5.update(bytes);
         byte[] hash = md5.digest();
         Formatter formatter = new Formatter();
         for (byte b : hash) {
            formatter.format("%02X", b);
         }
         smd5 = formatter.toString();
         formatter.close();
      } catch (Exception e) {
      }
      return smd5;
   }
}
