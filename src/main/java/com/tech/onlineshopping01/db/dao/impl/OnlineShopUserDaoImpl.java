package com.tech.onlineshopping01.db.dao.impl;

import com.tech.onlineshopping01.db.dao.OnlineShopUserDao;
import com.tech.onlineshopping01.db.mappers.onlineshop_userMapper;
import com.tech.onlineshopping01.db.po.onlineshop_user;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class OnlineShopUserDaoImpl implements OnlineShopUserDao {
    @Resource
    onlineshop_userMapper userMapper;
    @Override
    public int insertUser(onlineshop_user user) {
        return userMapper.insert(user);
    }

    @Override
    public onlineshop_user getUser(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
