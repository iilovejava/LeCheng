package com.lanou.dao;

import com.lanou.model.CartItem;

import java.util.List;

public interface CartItemMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CartItem record);

    int insertSelective(CartItem record);

    CartItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CartItem record);

    int updateByPrimaryKey(CartItem record);
<<<<<<< HEAD
    // 添加
    int addCartItem(CartItem cartItem);
    // 查看
    List<CartItem> selectCartByuserId(Integer userId);


=======

    List<CartItem> selectByUserId(Integer userid);

    int updateItem(CartItem cartItem);

    CartItem selectItemByUidAndPid(CartItem cartItem);

    int deleteItemById(Integer id);
>>>>>>> 40003e2c0a4795a9b01db9b07270d96d4c26e0b3
}