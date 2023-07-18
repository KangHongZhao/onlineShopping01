package com.tech.onlineshopping01.controller;

import com.tech.onlineshopping01.db.dao.CommodityDao;
import com.tech.onlineshopping01.db.dao.OrderDao;
import com.tech.onlineshopping01.db.po.commodity;
import com.tech.onlineshopping01.db.po.order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
public class OrderController {
    @Resource
    OrderDao orderDao;
    @Resource
    CommodityDao commodityDao;

    @RequestMapping("/commodity/buy/{userId}/{commodityId}")
    public String insertOrder(
            @PathVariable("userId") int userId,
            @PathVariable("commodityId") int commodityId,
            Map<String, Object> resultMap
    ){
        order onlineOrder = order.builder()
                .orderamount(1)
                .paytime(new Date())
                .commodityid(commodityId)
                .createtime(new Date())
                .orderstatus(1)
                .userid(userId)
                .orderid(1)
                .ordernum(UUID.randomUUID().toString())
                .paytime(new Date())
                .build();
        orderDao.insertOrder(onlineOrder);
        resultMap.put("resultInfo", "your order number is " + onlineOrder.getOrdernum());
        resultMap.put("orderNo", onlineOrder.getOrdernum());
        return "order_result";

    }
    @RequestMapping("/commodity/orderQuery/{orderNo}")
    public String getOrder(@PathVariable("orderNo") String orderNo, Map<String, Object> resultMap) {
        order orderByOrderNum = orderDao.getOrderByOrderNum(orderNo);
        commodity commodity = commodityDao.getCommodity(orderByOrderNum.getCommodityid());
        resultMap.put("order",orderByOrderNum);
        resultMap.put("commodity", commodity);
        return "order_check";

    }
}
