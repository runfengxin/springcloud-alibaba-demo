package com.xrf.config;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import static feign.FeignException.errorStatus;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
 
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 502) {
            return new RetryableException(response.status(), "Server 502", response.request().httpMethod(), new Date(), null);
        } else {
            return errorStatus(methodKey, response);
        }
    }
}