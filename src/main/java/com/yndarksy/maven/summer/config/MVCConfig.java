package com.yndarksy.maven.summer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MVCConfig implements WebMvcConfigurer {
    @Autowired
    private JWTInterceptor jwtInterceptor;
    /**
     * 静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/view/**")//设置URL映射路径
                .addResourceLocations("file:///E:\\UserImg\\");//文件路径
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/login","/user/vcode","/user/img","/view/**");
    }
}
