package com.graphy.platform.config;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class PlatformThymeleafConfig {

    @Value("${server.servlet.context-path}")
    private String webLevel;

    @Qualifier("thymeleafViewResolver")
    private final ThymeleafViewResolver thymeleafViewResolver;

    @Bean
    private void ThymeleafConfig() {
        if (thymeleafViewResolver != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("webLevel", webLevel);
            thymeleafViewResolver.setStaticVariables(data);
        }
    }
}
