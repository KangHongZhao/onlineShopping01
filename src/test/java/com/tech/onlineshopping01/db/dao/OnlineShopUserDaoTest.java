package com.tech.onlineshopping01.db.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.onlineshopping01.db.po.onlineshop_user;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class OnlineShopUserDaoTest {
    @Resource
    OnlineShopUserDao dao;
    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void insertUser() {
        onlineshop_user u = onlineshop_user.builder()
                .address("sdfsasdf").userid(1).email("11@11.cim").username("lip").password("123425").build();
        dao.insertUser(u);
    }

    @Test
    void getUser() throws JsonProcessingException {
        onlineshop_user user = dao.getUser(1);
        log.info(objectMapper.writeValueAsString(user));

    }
}