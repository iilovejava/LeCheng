package com.lanou.service.impl;

import com.lanou.dao.CartItemMapper;
import com.lanou.model.CartItem;
import com.lanou.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemMapper cartItemMapper;


    // 添加商品
    public int addCartItem(CartItem cartItem) {
        int result = cartItemMapper.addCartItem(cartItem);
        return result;
    }
    // 查看购物车
    public List<CartItem> selectCartByuserId(Integer userId) {
        List<CartItem> cartItems = cartItemMapper.selectCartByuserId(userId);
        for (CartItem cartItem : cartItems) {
            cartItem.getId();
        }
        return null;
    }

    // 查看购物车
   public List<CartItem> findProIdByUserId(Integer userId) {
//        List<CartItem> cartItems = cartItemMapper.findProIdByUserId(userId);
//        for (CartItem cartItem : cartItems) {
//            //cartItem.setProid();
//        }
       return null;
    }
}
