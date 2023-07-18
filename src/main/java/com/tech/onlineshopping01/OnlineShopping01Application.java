package com.tech.onlineshopping01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.tech.onlineshopping01.db.mappers")
@SpringBootApplication
public class OnlineShopping01Application {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShopping01Application.class, args);
    }

}

