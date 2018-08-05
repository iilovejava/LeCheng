package com.lanou.service;

import com.lanou.model.CartItem;

import java.util.List;

public interface CartItemService {

    // 查看购物车
    List<CartItem> selectByUserId(Integer userid);

    // 更改
    int updateItem(CartItem cartItem);

    // 添加
    int insert(CartItem record);

}
