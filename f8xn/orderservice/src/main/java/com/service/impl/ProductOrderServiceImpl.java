package com.service.impl;

import com.domain.ProductOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.service.ProductClient;
import com.service.ProductOrderService;
import com.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ProductClient productClient;

    @Override
    public ProductOrder save(int userId, int productId) {

        if(userId == 1){
            return null;
        }
        //调用订单服务
        String response = productClient.findById(productId);

        //调用用户服务,主要是获取用名称，用户的级别或者积分信息
        // TODO


        //积分服务 TODO

        logger.info("service save order");
        JsonNode  jsonNode = JsonUtils.str2JsonNode(response);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
        return productOrder;
    }
}
