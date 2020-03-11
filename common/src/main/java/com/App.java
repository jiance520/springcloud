package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
//@SpringBootApplication //不能加，否则引用引此包的无法打包！
public class App{
    public static void main(String[] args) {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class, args);
    }
}
