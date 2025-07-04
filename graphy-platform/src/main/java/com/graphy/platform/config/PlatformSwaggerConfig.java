package com.graphy.platform.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 秒杀的swagger文档
 *
 * @author LGH
 */
@Profile({"dev", "test"})
@EnableSwagger2
@Configuration("com.graphy.platform.config.PlatformSwaggerConfig")
@RequiredArgsConstructor
public class PlatformSwaggerConfig {

    private final ApiInfo apiInfo;

    @Bean
    public Docket platformRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .groupName("平台接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.graphy.platform.controller"))
                .paths(PathSelectors.any())
                .build();
    }


}
