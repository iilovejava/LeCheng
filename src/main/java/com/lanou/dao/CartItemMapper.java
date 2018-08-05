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

    List<CartItem> selectByUserId(Integer userid);

    int updateItem(CartItem cartItem);

    CartItem selectItemByUidAndPid(CartItem cartItem);

    int deleteItemById(Integer id);
}