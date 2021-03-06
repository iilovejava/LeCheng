package com.lanou.service;

import com.lanou.model.CartItem;
import com.lanou.model.Ding;
import com.lanou.model.Orders;
import com.lanou.model.ShopCart;

import java.util.List;

public interface CartItemService {

    // 查看购物车
    ShopCart selectByUserId(Integer userid);

    // 添加
    int insert(Integer priceid, Integer num, Integer userid);

    // 删除
    int deleteByPrimaryKey(Integer id);

    // 清空购物车
    List<CartItem> deleteAll(Integer userid);

    // 更改购物车商品数量
    int updateItem(Integer itemid,Integer num,Double price);

    // 购物车生成订单
    int addding(Integer userid);

    // 直接购物生成订单
    Orders buyding(String proname, String picture, Double price, Integer num, Double count, Integer userid);

    List<Ding> findOrder(Integer userid);

    CartItem selectByPrimaryKey(Integer id);

}
