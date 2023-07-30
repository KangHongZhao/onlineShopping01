package com.tech.onlineshopping01.component;

import com.tech.onlineshopping01.db.dao.CommodityDao;
import com.tech.onlineshopping01.db.po.commodity;
import com.tech.onlineshopping01.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class RedisPreHeat implements ApplicationRunner {
    @Resource
    CommodityDao commodityDao;

    @Resource
    RedisService redisService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<commodity> commodities = commodityDao.listCommoditiesByUserId(1);
        for (commodity com: commodities) {
            String redisKey = "commodityId:" + com.getCommodityid();
            redisService.setValue(redisKey, com.getAvailablestock().toString());
            log.info("preheat for commodity " + com.getCommodityid());
        }
    }
}
