package com.tech.onlineshopping01.db.dao;

import com.tech.onlineshopping01.db.po.commodity;
import com.tech.onlineshopping01.db.po.onlineshop_user;

public interface OnlineShopUserDao {
    int insertUser(onlineshop_user user);
    onlineshop_user getUser(int userId);
}
