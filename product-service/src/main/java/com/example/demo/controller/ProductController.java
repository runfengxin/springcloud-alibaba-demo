package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("product")
@RestController
public class ProductController {

    @RequestMapping("get/{pid}")
    public String getProduct(@PathVariable String pid) {
        return "2product+" + pid;
    }
}
