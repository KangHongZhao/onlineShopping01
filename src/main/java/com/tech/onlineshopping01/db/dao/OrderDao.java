package com.tech.onlineshopping01.db.dao;

import com.tech.onlineshopping01.db.po.commodity;
import com.tech.onlineshopping01.db.po.order;

public interface OrderDao {
    int insertOrder(order record);
    order getOrder(int orderId);

    order getOrderByOrderNum(String orderNum);

    int updateOrder(order orderDetail);
}
