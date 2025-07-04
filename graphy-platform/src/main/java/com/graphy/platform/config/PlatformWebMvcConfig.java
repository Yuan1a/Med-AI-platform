package com.graphy.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration("com.graphy.platform.config.platformwebmvcconfig")
public class PlatformWebMvcConfig implements WebMvcConfigurer {


    @Bean
    public PlatformRequestInterceptor getPlatformRequestInterceptor() {
        return new PlatformRequestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getPlatformRequestInterceptor())
                .addPathPatterns("/pf/**")
                .excludePathPatterns(
                        "/pf/PlatformFile/pdf/**",
                        "/pf/BusUserArchive/getRegion",
                        "/pf/BusExamineSign/jzqCallBack",
                        "/pf/PlatformLogin/**",
                        "/pf/PlatformError/**",
                        "/pf/PlatformImage/load/**"
                );
    }

}
