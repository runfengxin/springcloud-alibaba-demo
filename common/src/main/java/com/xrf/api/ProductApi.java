package com.xrf.api;

import com.xrf.config.CommonFeignRetryConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "product-service", configuration = CommonFeignRetryConfig.class)
public interface ProductApi {

    @GetMapping("/getProductInfo")
    String getProductInfo(@RequestParam("message") String message);
}
