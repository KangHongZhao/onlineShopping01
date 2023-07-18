package com.tech.onlineshopping01.db.mappers;

import com.tech.onlineshopping01.db.po.onlineshop_user;

public interface onlineshop_userMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(onlineshop_user record);

    int insertSelective(onlineshop_user record);

    onlineshop_user selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(onlineshop_user record);

    int updateByPrimaryKey(onlineshop_user record);
}