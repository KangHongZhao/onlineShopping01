package com.tech.onlineshopping01.config;

import com.tech.onlineshopping01.module.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class UserConfig {
    @Bean
    public User getUser() {
        return User.builder().userName("Nobody").userEmail("Nobody@gmail.com").build();
    }
}
