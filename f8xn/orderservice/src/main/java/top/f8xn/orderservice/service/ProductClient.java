package top.f8xn.orderservice.service;

import top.f8xn.orderservice.fallbcak.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品服务客户端，
 */
@FeignClient(name = "productservice", fallback = ProductClientFallback.class) //超时时调用fallback，返回null
//@FeignClient(name = "productservice")
public interface ProductClient {

    @GetMapping("/api/v1/product/find")
    String findById(@RequestParam(value = "id") int id);

}
