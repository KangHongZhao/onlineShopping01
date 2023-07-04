package com.tech.onlineshopping01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world";
    }
    @GetMapping("/echo/{text}")
    public String helloWorld1(@PathVariable("text") String abc) {
        return abc;
    }
}
