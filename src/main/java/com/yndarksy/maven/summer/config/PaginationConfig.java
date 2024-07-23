package com.yndarksy.maven.summer.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaginationConfig {
    @Bean
    PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
