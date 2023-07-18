package com.tech.onlineshopping01.config;

import com.tech.onlineshopping01.module.online_User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class UserConfig {
    @Bean
    public online_User getUser() {
        return online_User.builder().userName("Nobody").userEmail("Nobody@gmail.com").build();
    }
}
