package com.graphy.service.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@Configuration("om.own.framework.service.config.baseswaggerconfig")
public class BaseSwaggerConfig {


    @Bean
    public ApiInfo baseInfo() throws Exception {
        return new ApiInfoBuilder()
                .title("")
                .description("接口文档Swagger版")
                .version("1.0")
                .build();
    }
}

