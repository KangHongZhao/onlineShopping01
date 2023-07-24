package com.tech.onlineshopping01.db.dao.impl;

import com.tech.onlineshopping01.db.dao.CommodityDao;
import com.tech.onlineshopping01.db.mappers.commodityMapper;
import com.tech.onlineshopping01.db.po.commodity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CommodityDaoImpl implements CommodityDao {
    @Resource
    commodityMapper commoditymapper;
    @Override
    public int insertCommodity(commodity record) {
        return commoditymapper.insert(record);
    }

    @Override
    public commodity getCommodity(int commodityId) {
        return commoditymapper.selectByPrimaryKey(commodityId);
    }

    @Override
    public List<com.tech.onlineshopping01.db.po.commodity> listCommoditiesByUserId(int userId) {
        return commoditymapper.listCommoditiesByUserId(userId);
    }

    @Override
    public int updateCommodity(commodity record) {
        return commoditymapper.updateByPrimaryKey(record);
    }

    @Override
    public int deductStock(int commodityId) {
        return commoditymapper.deductStock(commodityId);
    }
}
