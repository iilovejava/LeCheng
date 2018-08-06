package com.lanou.dao;

import com.lanou.model.ShopCart;

public interface ShopCartMapper {
    int insert(ShopCart record);

    int insertSelective(ShopCart record);

    ShopCart selectCart(Integer userId);
}