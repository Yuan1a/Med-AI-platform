package com.graphy.unit.http.assist;

import java.net.URLConnection;
import java.util.Map;

/**
 * 公共使用
 */
public class Common {
   /**
    * 请求头
    *
    * @param conn
    * @param headers
    */
   public static void addHeaders(URLConnection conn, Map<String, String> headers) {
      //添加请求头
      if (headers != null && headers.size() > 0) {
         for (Map.Entry<String, String> entry : headers.entrySet()) {
            conn.setRequestProperty(entry.getKey(), entry.getValue());
         }
      }
   }



}
