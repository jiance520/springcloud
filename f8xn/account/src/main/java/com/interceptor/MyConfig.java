package com.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 加拦截器
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/loginaction");
        /*对指定的登陆action方法使用拦截器，idea使用拦截器+token.jsp和令牌uuid-token防止登陆重复提交表单*/
    }
}
