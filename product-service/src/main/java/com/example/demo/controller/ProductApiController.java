package com.example.demo.controller;

import com.xrf.api.ProductApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApiController implements ProductApi {
    @Override
    @GetMapping("/getProductInfo")
    public String getProductInfo(String message) {
        return "oook";
    }
}
