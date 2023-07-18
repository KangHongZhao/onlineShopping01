package com.tech.onlineshopping01.db.dao;

import com.tech.onlineshopping01.db.po.commodity;

import java.util.List;

public interface CommodityDao {
    int insertCommodity(commodity record);
    commodity getCommodity(int commodityId);

    List<commodity> listCommoditiesByUserId(int userId);

}
