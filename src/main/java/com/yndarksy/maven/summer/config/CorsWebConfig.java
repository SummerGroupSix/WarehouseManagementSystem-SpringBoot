package com.yndarksy.maven.summer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsWebConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        //允许那些域名跨域
        configuration.addAllowedOriginPattern("*");
        //允许所有请求方式跨域
        configuration.addAllowedMethod("*");
        //允许所有请求头跨域访问
        configuration.addAllowedHeader("*");
        //允许前端请求携带认证信息前后端要一致
        configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}
