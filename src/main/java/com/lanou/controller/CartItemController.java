package com.lanou.controller;


import com.lanou.dao.OrderMapper;
import com.lanou.dao.*;
import com.lanou.model.*;

import com.lanou.dao.ShopCartMapper;
import com.lanou.model.CartItem;

import com.lanou.model.ShopCart;

import com.lanou.service.CartItemService;
import com.lanou.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "cart")

public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ValueMapper valueMapper;
    @Autowired
    private PriceMapper priceMapper;
    @Autowired
    private PictureMapper pictureMapper;


    // 查看购物车
    @ResponseBody
    @RequestMapping(value = "find")
    public ShopCart selectCart(Integer userid) {
        ShopCart shopCart = shopCartMapper.selectCart(userid);
        List<CartItem> items = cartItemService.selectByUserId(userid);
        Double amount = 0D;
        for (CartItem item : items) {
            // 获得单价
            item.setUnitPrice(item.getCount()/item.getNum());
            // 获得描述
            Price price = priceMapper.findPriceBypriId(item.getPriceid());
            String one;
            String two;
            String three;
            Value v1 = valueMapper.selectByPrimaryKey(price.getOne());
            if (v1 == null){
                one = " ";
            } else {
                one = v1.getValue();
            }
            Value v2 = valueMapper.selectByPrimaryKey(price.getTwo());
            if (v2 == null){
                two = " ";
            } else {
                two = v2.getValue();
            }
            Value v3 = valueMapper.selectByPrimaryKey(price.getThree());
            if (v3 == null){
                three = " ";
            } else {
                three = v3.getValue();
            }
            // 描述
            item.setNorms(one +" " + two + " " + three);
            // 商品id
            Product product = productMapper.selectProid(item.getProname());
            item.setProid(product.getProductid());
            // 总计
            amount += item.getCount();
        }
        shopCart.setCount(amount);
        shopCart.setItems(items);
        shopCartMapper.updateCart(shopCart);
        return shopCart;
    }

    // 添加商品
    @ResponseBody
    @RequestMapping(value = "add")
    public ServiceResponse addCartItem(Integer priceid, Integer num,Integer userid) {
        Price price = priceMapper.selectByPrimaryKey(priceid);
        // 根据userId遍历购物车
        List<CartItem> items = cartItemService.selectByUserId(userid);
        // 遍利购物车
        for (CartItem item : items) {
            // 需要根据priceId判断购物车是否已有该商品
            if (item.getPriceid() == priceid){
                // 商品已在购物车 数量相加
                item.setNum(item.getNum() + num);
                int n = item.getNum();
                Price unit = priceMapper.findPriceBypriId(item.getPriceid());
                // 更改小计
                item.setCount(unit.getPrice() * n);
                // 更改数据库中商品数量和小计
                int res = cartItemService.updateItem(item);
                if (res != 1) {
                    return ServiceResponse.createError(1,"添加购物车失败");
                }
                return ServiceResponse.createSuccess("添加成功");
            }
        }
        CartItem newItem = new CartItem();
        Product product = productMapper.selectByPrimaryKey(price.getProid());
        // 商品名称
        newItem.setProname(product.getProductname());

        List<Picture> list = pictureMapper.selectPicture(product.getProductid());
        // 商品图片
        newItem.setPicture(list.get(0).getPicurl());
        // 价格id
        newItem.setPriceid(priceid);
        // 数量
        newItem.setNum(num);
        // 小计
        newItem.setCount(price.getPrice() * num);
       //  用户
        newItem.setUserid(userid);
        // 添加到商品列表
        int res = cartItemService.insert(newItem);

        if(res == 1) {
            ServiceResponse serviceResponse = ServiceResponse.createSuccess("添加购物车成功");
            // 关联用户购物车
            ShopCart shopCart = shopCartMapper.selectCart(userid);
            if (shopCart == null) {
                ShopCart newCart = new ShopCart();
                newCart.setUserid(userid);
                shopCartMapper.insert(newCart);
                return serviceResponse;
            }
            return serviceResponse;
        } else {
            ServiceResponse serviceResponse = ServiceResponse.createError(1,"添加失败");
            return serviceResponse;
        }
    }




      // 修改商品数量
    @ResponseBody
    @RequestMapping(value = "num")
    public ServiceResponse updateItem(Integer itemid,Integer num,Double price) {
        CartItem item = cartItemService.selectByPrimaryKey(itemid);
        item.setNum(num);
        Double con = item.getCount();
        item.setCount(price * num);
        int res = cartItemService.updateItem(item);
        if (res == 1) {
            ShopCart shopCart = shopCartMapper.selectCart(item.getUserid());
            Double com = shopCart.getCount();
            shopCart.setCount(com - con + (price * num));
            int result = shopCartMapper.updateCart(shopCart);
            if (result != 1) {
                return ServiceResponse.createError(1,"购物车数据更新失败");
            } else {
                return ServiceResponse.createSuccess("购物车数据更新成功");
            }
        }
        return ServiceResponse.createError(1,"商品项更新失败");
        
    }


        // 移除商品
        @ResponseBody
        @RequestMapping(value = "delete")
        public ServiceResponse deleteItem (Integer itemid){
            // 根据id移除item
            int res = cartItemService.deleteByPrimaryKey(itemid);
            if (res != 1) {
                return ServiceResponse.createError(1, "商品移除失败");
            }
            return ServiceResponse.createSuccess("移除成功");
        }

        // 清空购物车
        @ResponseBody
        @RequestMapping(value = "deleteAll")
        public ServiceResponse deleteAll (Integer userid){
            List<CartItem> items = cartItemService.selectByUserId(userid);
            for (CartItem item : items) {
                int res = cartItemService.deleteByPrimaryKey(item.getId());
                items.remove(item);
            }
            if (items.size() != 0) {
                return ServiceResponse.createError(1,"清空购物车失败");
            }
            return ServiceResponse.createSuccess("清空购物车成功");
        }

    }
