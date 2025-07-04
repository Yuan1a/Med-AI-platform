package com.graphy.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration("com.graphy.service.config.requestwebmvcconfig")
public class RequestWebMvcConfig implements WebMvcConfigurer {
    @Bean
    public RequestServiceInterceptor getRequestServiceInterceptor() {
        return new RequestServiceInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getRequestServiceInterceptor())
                .addPathPatterns("/**");
    }
}
