package com.tech.onlineshopping01.db.mappers;

import com.tech.onlineshopping01.db.po.commodity;

import java.util.List;
import java.util.Map;

public interface commodityMapper {
    int deleteByPrimaryKey(Integer commodityid);

    int insert(commodity record);

    int insertSelective(commodity record);

    commodity selectByPrimaryKey(Integer commodityid);

    int updateByPrimaryKeySelective(commodity record);

    int updateByPrimaryKey(commodity record);

    List<commodity> listCommoditiesByUserId(int userId);

    int deductStock(int commodityId);


    void deductStockSp(Map<String, Object> map);
}