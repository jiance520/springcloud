package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//1.5.8.RELEASE 版本
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//2.0.1.RELEASE 版本

//spring boot工程结合web工程需要此类
/*@ServletComponentScan
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)*/
public class SpringBootStartApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Autof8App.class);
    }
}
