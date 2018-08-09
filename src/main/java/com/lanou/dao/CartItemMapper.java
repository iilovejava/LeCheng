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

    // 添加
    int addCartItem(CartItem cartItem);
<<<<<<< HEAD
=======
    // 查看
    List<CartItem> selectCartByuserId(Integer userId);


>>>>>>> 74c3bd3e53d0f100481beafbe437cd82482b2938

    // 根据用户id查出商品
    List<CartItem> selectByUserId(Integer userid);

    // 修改商品数量和小计
    int updateItem(CartItem item);

    // 根据proid和priceid获得item
    CartItem findItemByproIdAndpriId(CartItem cartItem);

<<<<<<< HEAD
}
=======
    // 移除商品
    int deleteItemById(Integer id);
}
>>>>>>> 74c3bd3e53d0f100481beafbe437cd82482b2938
