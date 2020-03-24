package com;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//1.5.8.RELEASE 版本
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//2.0.1.RELEASE 版本

//spring boot工程结合web工程需要此类
public class SpringBootStartApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Buy51ctoApp.class);
    }
}
