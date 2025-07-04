package com.graphy.service.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("com.graphy.service.config.mybatisplusconfig")
@MapperScan("com.graphy.**.mapper")
public class MybatisPlusConfig {
    // 最新版
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        interceptor.addInnerInterceptor(getMybatisPlusUpdateInterceptor());
        return interceptor;
    }

    /**
     * 数据库编辑拦截
     *
     * @return
     */
    @Bean
    public MybatisPlusUpdateInterceptor getMybatisPlusUpdateInterceptor() {
        return new MybatisPlusUpdateInterceptor();
    }
}
