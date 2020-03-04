package com.fallbcak;

import com.service.ProductClient;
import org.springframework.stereotype.Component;

/**
 * 针对商品服务，错降级处理，一但降级，则无法返回数据，
 */
@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public String findById(int id) {

        System.out.println("feign 调用productservice findbyid 异常");

        return null;
    }



}
