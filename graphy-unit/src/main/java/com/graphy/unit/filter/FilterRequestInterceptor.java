package com.graphy.unit.filter;
 import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
@SuppressWarnings("all")
public class FilterRequestInterceptor extends HttpServletRequestWrapper {
   private byte[] body = null;

   public FilterRequestInterceptor(HttpServletRequest request) throws IOException {
      super(request);
      int totalBytes = request.getContentLength();
      if (totalBytes > 0) {
         //将request的输入流读入到bytes中
         InputStream inputStream = request.getInputStream();
         DataInputStream dataInputStream = new DataInputStream(inputStream);
         body = new byte[totalBytes];
         dataInputStream.readFully(body);
         dataInputStream.close();
      }
   }


   @Override
   public BufferedReader getReader() throws IOException {
      return new BufferedReader(new InputStreamReader(getInputStream()));
   }

   @Override
   public ServletInputStream getInputStream() throws IOException {
      final ByteArrayInputStream bais = new ByteArrayInputStream(body);
      return new ServletInputStream() {
         @Override
         public int read() throws IOException {
            return bais.read();
         }

         @Override
         public boolean isFinished() {
            return false;
         }

         @Override
         public boolean isReady() {
            return false;
         }

         @Override
         public void setReadListener(ReadListener readListener) {

         }

      };
   }

   @Override
   public String getParameter(String name) {
      try {
         // 返回值之前 先进行过滤
         return com.graphy.unit.dangerstring.Api.filterStripXss(super.getParameter(com.graphy.unit.dangerstring.Api.filterStripXss(name)));
      } catch (Exception err) {
         return super.getParameter(name);
      }

   }

   @Override
   public String[] getParameterValues(String name) {
      // 返回值之前 先进行过滤
      String[] values = super.getParameterValues(com.graphy.unit.dangerstring.Api.filterStripXss(name));
      if (values != null) {
         for (int i = 0; i < values.length; i++) {
            values[i] = com.graphy.unit.dangerstring.Api.filterStripXss(values[i]);
         }
      }
      return values;
   }

}
