package com.tech.onlineshopping01.controller;

import com.tech.onlineshopping01.db.dao.CommodityDao;
import com.tech.onlineshopping01.db.dao.OrderDao;
import com.tech.onlineshopping01.db.po.commodity;
import com.tech.onlineshopping01.db.po.order;
import com.tech.onlineshopping01.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
public class OrderController {
    @Resource
    OrderDao orderDao;
    @Resource
    CommodityDao commodityDao;

    @Resource
    OrderService orderService;

    @RequestMapping("/commodity/buy/{userId}/{commodityId}")
    public String processOrderAllInOneSql(
            @PathVariable("userId") int userId,
            @PathVariable("commodityId") int commodityId,
            Map<String, Object> resultMap
    ){
        //order onlineOrder = orderService.processOrderAllInOneSql(commodityId, userId);
        //order onlineOrder = orderService.processOrderSP(commodityId, userId);
        //order onlineOrder = orderService.processOrderRedis(commodityId, userId);
        order onlineOrder = orderService.processOrderDistributedLock(commodityId, userId);
        if (onlineOrder == null) {
            return "error";
        }
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
    @RequestMapping("/commodity/payOrder/{orderNo}")
    public String payOrder(@PathVariable("orderNo") String orderNo) {
        orderService.payOrderProcessing(orderNo);
        return "redirect:/commodity/orderQuery/" + orderNo;
    }
}
