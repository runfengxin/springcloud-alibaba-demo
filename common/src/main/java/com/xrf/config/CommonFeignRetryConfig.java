package com.xrf.config;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonFeignRetryConfig {

    @Bean
    ErrorDecoder getError() {
        return new FeignErrorDecoder();
    }
    @Bean
    public Retryer getRetryBean() {
        return new CommonFeignRetry(true);
    }
   //@FeignClient注解指定配置
}