package com.example.demo.controller;

import com.xrf.api.ProductApi;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
@RefreshScope
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ProductApi productApi;

    @RequestMapping("get")
    public String get() {
        return restTemplate.getForObject("http://product-service/product/get/854645907", String.class);
    }

    @RequestMapping("working")
    public String working() throws InterruptedException {
        return productApi.getProductInfo("aaa");
    }
}
