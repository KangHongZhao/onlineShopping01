package com.tech.onlineshopping01.db.dao.impl;

import com.tech.onlineshopping01.db.dao.OrderDao;
import com.tech.onlineshopping01.db.mappers.orderMapper;
import com.tech.onlineshopping01.db.po.order;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Resource
    orderMapper order;
    @Override
    public int insertOrder(order record) {
        return order.insert(record);
    }

    @Override
    public order getOrder(int orderId) {
        return order.selectByPrimaryKey(orderId);
    }

    @Override
    public com.tech.onlineshopping01.db.po.order getOrderByOrderNum(String orderNum) {
        return order.getOrderByOrderNum(orderNum);
    }
}
