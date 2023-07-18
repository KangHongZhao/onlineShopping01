package com.tech.onlineshopping01.db.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.onlineshopping01.db.dao.CommodityDao;
import com.tech.onlineshopping01.db.po.commodity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CommodityDaoImplTest {
    @Resource
    CommodityDao commoditydao;
    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void insertCommodity() {
        commodity comm = commodity.builder()
                .description("111")
                .availablestock(4)
                .commodityid(111)
                .commodityname("asdgs")
                .price((float)5.2)
                .sellerid(1)
                .totalstock(10).build();
        commoditydao.insertCommodity(comm);
    }
    @Test
    void getCommodity() throws JsonProcessingException {
        commodity commodit = commoditydao.getCommodity(1);
        log.info(objectMapper.writeValueAsString(commodit));
    }

    @Test
    void listByUserId() throws JsonProcessingException {
        List<commodity> commodit = commoditydao.listCommoditiesByUserId(1);
        log.info(objectMapper.writeValueAsString(commodit));
    }


}