package com.graphy.unit.wechat.assist;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WxRequest {
   /**
    * 微信支付请求
    *
    * @param url
    * @param method
    * @param content
    * @return
    * @throws Exception
    */
   public static byte[] WxPayRequest(String url, String method, String content) throws Exception {
      InputStream in = null;
      ByteArrayOutputStream bo = null;
      byte[] result = null;
      try {
         // 创建SSLContext对象，并使用我们指定的信任管理器初始化
         TrustManager[] tm = {new WxX509TrustManager()};
         SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
         sslContext.init(null, tm, new java.security.SecureRandom());
         // 从上述SSLContext对象中得到SSLSocketFactory对象
         SSLSocketFactory ssf = sslContext.getSocketFactory();
         HttpURLConnection urlc = (HttpURLConnection) new URL(url).openConnection();
         methodValue(urlc, "setSSLSocketFactory", ssf);
         urlc.setDoOutput(true);
         urlc.setDoInput(true);
         urlc.setUseCaches(false);
         // 设置请求方式（GET/POST）
         urlc.setRequestMethod(method);
         if ("GET".equalsIgnoreCase(method)) {
            urlc.connect();
         }
         // 当有数据需要提交时
         if (null != content) {
            OutputStream out = urlc.getOutputStream();
            // 注意编码格式，防止中文乱码
            out.write(content.getBytes("UTF-8"));
            out.close();
         }

         in = urlc.getInputStream();
         bo = new ByteArrayOutputStream();
         byte[] b = new byte[1024];
         int l;
         while ((l = in.read(b)) > 0) {
            bo.write(b, 0, l);
         }
         result = bo.toByteArray();
         urlc.disconnect();
      } catch (ConnectException ce) {
         throw ce;
      } catch (Exception e) {
         throw e;
      } finally {
         if (in != null) {
            try {
               in.close();
            } catch (IOException e) {
            }
         } else if (bo != null) {
            try {
               bo.close();
            } catch (IOException e) {
            }
         }
      }
      return result;
   }

   /**
    * 微信支付专用
    *
    * @param bean
    * @param methodName
    * @param params
    * @return
    */
   private static Object methodValue(Object bean, String methodName, Object... params) {
      if (bean != null && methodName != null && (methodName = methodName.trim()).length() != 0) {
         Method method = null;
         if (params == null) {
            return methodValue(bean, methodName);
         } else {
            Class[] classes = new Class[params.length];

            for (int i = 0; i < params.length; ++i) {
               classes[i] = params[i] != null ? params[i].getClass() : String.class;
            }

            try {
               method = bean.getClass().getDeclaredMethod(methodName.trim(), classes);
            } catch (Exception var12) {
               Method[] methods = bean.getClass().getDeclaredMethods();
               Class[] types = null;
               boolean isType = false;

               for (int i = 0; i < methods.length; ++i) {
                  if (methods[i].getName().equals(methodName)) {
                     types = methods[i].getParameterTypes();
                     if (types.length == classes.length) {
                        isType = false;
                        for (int j = 0; j < types.length; ++j) {
                           if (!types[j].isAssignableFrom(classes[j])) {
                              isType = false;
                              break;
                           }
                           isType = true;
                        }
                        if (isType) {
                           method = methods[i];
                           break;
                        }
                     }
                  }
               }
            }

            if (method == null) {
               return null;
            } else {
               try {
                  return method.invoke(bean, params);
               } catch (Exception var11) {
                  return null;
               }
            }
         }
      } else {
         return null;
      }
   }
}
