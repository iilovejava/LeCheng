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

    // 根据用户id查看购物车
    public List<CartItem> selectByUserId(Integer userid) {
        List<CartItem> items = cartItemMapper.selectByUserId(userid);
        return items;
    }

    // 修改item信息
    public int updateItem(CartItem cartItem) {
        int res = cartItemMapper.updateItem(cartItem);
        return res;
    }

    // 添加商品
    public int insert(CartItem record) {
        int res = cartItemMapper.insert(record);
        return res;
    }

    public CartItem findItemByproIdAndpriId(CartItem cartItem) {
        CartItem item = cartItemMapper.findItemByproIdAndpriId(cartItem);
        return item;
    }


    // 移除商品
    public int deleteItemById(Integer id) {
        int res = cartItemMapper.deleteItemById(id);
        return res;
    }





}
