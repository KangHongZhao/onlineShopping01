package com.tech.onlineshopping01.db.mappers;

import com.tech.onlineshopping01.db.po.order;

public interface orderMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(order record);

    int insertSelective(order record);

    order selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(order record);

    int updateByPrimaryKey(order record);


    order getOrderByOrderNum(String orderNum);
}