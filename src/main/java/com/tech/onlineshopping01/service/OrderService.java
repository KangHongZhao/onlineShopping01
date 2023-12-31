package com.tech.onlineshopping01.service;

import com.tech.onlineshopping01.db.dao.CommodityDao;
import com.tech.onlineshopping01.db.dao.OrderDao;
import com.tech.onlineshopping01.db.po.commodity;
import com.tech.onlineshopping01.db.po.order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class OrderService {
    @Resource
    OrderDao orderDao;
    @Resource
    CommodityDao commodityDao;

    @Resource
    RedisService redisService;
    public order processOrderAllInOneSql(int commodityId, int userId) {
        commodity com = commodityDao.getCommodity(commodityId);
        int availableStock = com.getAvailablestock();
        if (availableStock <= 0) {
            log.error("out of stock");
            return null;
        }
        int result = commodityDao.deductStock(commodityId);
        if (result == 0) {
            log.error("out of stock");
            return null;
        } else {
            order onlineOrder = order.builder()
                    .orderamount((int)Math.floor(com.getPrice()))
                    .commodityid(commodityId)
                    .createtime(new Date())
                    .orderstatus(1)
                    .userid(userId)
                    .ordernum(UUID.randomUUID().toString())
                    .build();
            orderDao.insertOrder(onlineOrder);
            return onlineOrder;
        }
    }
    public order processOrder(int commodityId, int userId) {
        commodity com = commodityDao.getCommodity(commodityId);
        int availableStock = com.getAvailablestock();
        if (availableStock <= 0) {
            log.error("out of stock");
            return null;
        }
        availableStock--;
        com.setAvailablestock(availableStock);
        commodityDao.updateCommodity(com);
        order onlineOrder = order.builder()
                .orderamount((int)Math.floor(com.getPrice()))
                .commodityid(commodityId)
                .createtime(new Date())
                .orderstatus(1)
                .userid(userId)
                .ordernum(UUID.randomUUID().toString())
                .build();
        orderDao.insertOrder(onlineOrder);
        return onlineOrder;
    }
    // 0 -- no
    // 1 -- pending
    // 2 -- finish
    // 99 -- invalid order, timeout
    public void payOrderProcessing(String orderNo) {
        order orderDetail = orderDao.getOrderByOrderNum(orderNo);
        if (orderDetail == null || orderDetail.getOrderstatus() != 1) {
            log.error("can't process order: " + orderNo);
            return;
        } else {
            orderDetail.setOrderstatus(2);
            orderDetail.setPaytime(new Date());
            orderDao.updateOrder(orderDetail);
            return;
        }
    }

    public order processOrderSP(int commodityId, int userId) {


        int result = commodityDao.deductStockSP(commodityId);
        if (result > 0) {
            commodity com = commodityDao.getCommodity(commodityId);
            order onlineOrder = order.builder()
                    .orderamount((int)Math.floor(com.getPrice()))
                    .commodityid(commodityId)
                    .createtime(new Date())
                    .orderstatus(1)
                    .userid(userId)
                    .ordernum(UUID.randomUUID().toString())
                    .build();
            orderDao.insertOrder(onlineOrder);
            log.info("success created order, commodityId is" + commodityId);
            return onlineOrder;
        } else {
            log.info("process order failed, due to no available stock, commodityId is : " + commodityId);
            return null;
        }

    }


    public order processOrderRedis(int commodityId, int userId) {


        String redisKey = "commodityId:" + commodityId;
        long result = redisService.stockDeduct(redisKey);
        if (result >= 0) {
            commodityDao.deductStock(commodityId);
            commodity com = commodityDao.getCommodity(commodityId);
            order onlineOrder = order.builder()
                    .orderamount((int) Math.floor(com.getPrice()))
                    .commodityid(commodityId)
                    .createtime(new Date())
                    .orderstatus(1)
                    .userid(userId)
                    .ordernum(UUID.randomUUID().toString())
                    .build();
            orderDao.insertOrder(onlineOrder);
            log.info("success created order, commodityId is" + commodityId);
            return onlineOrder;
        } else {
            log.info("process order failed, due to no available stock, commodityId is : " + commodityId);
            return null;
        }
    }

    public order processOrderDistributedLock(int commodityId, int userId) {


        String lockKey = "lockCommodity:" + commodityId;
        String requestId = UUID.randomUUID().toString();
        boolean result = redisService.tryGetDistributedLock(lockKey, requestId, 10000);
        if (result) {
            log.info("process order success for commodity:" + commodityId);
            commodityDao.deductStock(commodityId);
            commodity com = commodityDao.getCommodity(commodityId);
            order onlineOrder = order.builder()
                    .orderamount((int) Math.floor(com.getPrice()))
                    .commodityid(commodityId)
                    .createtime(new Date())
                    .orderstatus(1)
                    .userid(userId)
                    .ordernum(UUID.randomUUID().toString())
                    .build();
            orderDao.insertOrder(onlineOrder);
            log.info("success created order, commodityId is" + commodityId);
            redisService.releaseDistributedLock(lockKey, requestId);
            return onlineOrder;
        } else {
            log.info("lock fail for commodity:" + commodityId);
            return null;
        }
    }
}
