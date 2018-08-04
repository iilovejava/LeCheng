package com.lanou.service;

import com.lanou.model.CartItem;

import java.util.List;

public interface CartItemService {

    int addCartItem(CartItem cartItem);

    List<CartItem> selectCartByuserId(Integer userId);
}
