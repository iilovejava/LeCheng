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
=======

    // 添加
    int addCartItem(CartItem cartItem);
    // 查看
    List<CartItem> selectCartByuserId(Integer userId);


>>>>>>> d04a27beab9b55c0616597743fe6512207420dd6

    // 根据用户id查出商品
    List<CartItem> selectByUserId(Integer userid);

    // 修改商品数量和小计
    int updateItem(CartItem item);

    // 根据proid和priceid获得item
    CartItem findItemByproIdAndpriId(CartItem cartItem);

    // 移除商品
    int deleteItemById(Integer id);
<<<<<<< HEAD
=======

>>>>>>> d04a27beab9b55c0616597743fe6512207420dd6
}