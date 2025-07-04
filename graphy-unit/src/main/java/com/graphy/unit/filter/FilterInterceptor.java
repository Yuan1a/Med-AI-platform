package com.graphy.unit.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@SuppressWarnings("all")
public class FilterInterceptor implements Filter {


   @Override
   public void init(FilterConfig filterConfig) throws ServletException {

   }

   @Override
   public void doFilter(ServletRequest req, ServletResponse res,
                        FilterChain chain) throws IOException, ServletException {
      HttpServletRequest request = (HttpServletRequest) req;
      String url = request.getRequestURI();
      ServletRequest requestWrapper = null;
      if (request instanceof HttpServletRequest) {
         requestWrapper = new FilterRequestInterceptor((HttpServletRequest) request);
      }
      if (null == requestWrapper) {
         chain.doFilter(request, res);
      } else {
         chain.doFilter(requestWrapper, res);
      }
   }


   @Override
   public void destroy() {

   }

}
