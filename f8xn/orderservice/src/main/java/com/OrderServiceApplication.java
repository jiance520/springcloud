package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
//@SpringCloudApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //以下不加也可以!用于方式二在类里面配置负载策略，方式一是在yml配置文件里配置。
//    @Bean
//    public IRule ribbonRule() {
//        return new RandomRule();//这里配置策略，和配置文件对应，如果配置文件是RoundRobinRule，写return new RoundRobinRule();/
//    }

}
