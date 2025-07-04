package com.graphy.service.config;

import com.graphy.service.service.PlatformRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 系统请求拦截
 */
@Component("com.graphy.service.config.requestserviceinterceptor")
public class RequestServiceInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private PlatformRequestService platformRequestService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        platformRequestService.insertRequest(request, response, handler);
        return super.preHandle(request, response, handler);
    }

}
