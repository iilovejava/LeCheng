package com.lanou.service.impl;

import com.lanou.dao.*;
import com.lanou.model.*;
import com.lanou.service.CartItemService;
import com.lanou.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private PriceMapper priceMapper;
    @Autowired
    private ValueMapper valueMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private DingMapper dingMapper;

    // 根据用户id查看购物车
    public ShopCart selectByUserId(Integer userid) {
        ShopCart shopCart = shopCartMapper.selectCart(userid);
        List<CartItem> items = cartItemMapper.selectByUserId(userid);
        Double amount = 0D;
        for (CartItem item : items) {
            // 获得单价
            item.setUnitPrice(item.getCount() / item.getNum());
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
            // 商品id
            Product product = productMapper.selectProid(item.getProname());
            item.setProid(product.getProductid());
            // 商品描述
            item.setNorms(one + " " + two + " " + three);
            // 总计
            amount += item.getCount();
        }
        shopCart.setCount(amount);
        shopCart.setItems(items);
        shopCartMapper.updateCart(shopCart);
        return shopCart;
    }

    // 添加商品
    public int insert(Integer priceid, Integer num, Integer userid) {
        Price price = priceMapper.findPriceBypriId(priceid);
        // 根据userId遍历购物车
        List<CartItem> items = cartItemMapper.selectByUserId(userid);
        // 遍利购物车
        for (CartItem item : items) {
            // 需要根据priceId判断购物车是否已有该商品
            if (item.getPriceid() == priceid) {
                // 商品已在购物车 数量相加
                item.setNum(item.getNum() + num);
                int n = item.getNum();
                Price unit = priceMapper.findPriceBypriId(item.getPriceid());
                // 更改小计
                item.setCount(unit.getPrice() * n);
                // 更改数据库中商品数量和小计
                int res = cartItemMapper.updateItem(item);
                return res;
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
        int res = cartItemMapper.insert(newItem);
        return res;
    }

    // 移除商品
    public int deleteByPrimaryKey(Integer id) {
        int res = cartItemMapper.deleteByPrimaryKey(id);
        return res;
    }

    // 清空购物车
    public List<CartItem> deleteAll(Integer userid) {
        List<CartItem> items = cartItemMapper.selectByUserId(userid);
        Iterator<CartItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            cartItemMapper.deleteByPrimaryKey(item.getId());
            items.remove(item);
        }
        return items;
    }

    // 修改item数量
    public int updateItem(Integer itemid,Integer num,Double price) {

        CartItem item = cartItemMapper.selectByPrimaryKey(itemid);
        item.setNum(num);
        Double con = item.getCount();
        item.setCount(price * num);
        int res = cartItemMapper.updateItem(item);
        if (res == 1) {
            ShopCart shopCart = shopCartMapper.selectCart(item.getUserid());
            Double com = shopCart.getCount();
            shopCart.setCount(com - con + (price * num));
            int result = shopCartMapper.updateCart(shopCart);
            return result;
        }
        return res;
    }

    // 购物车生成订单
    public int addding(Integer userid) {
        System.out.println(userid);
        Format format = new SimpleDateFormat("yyyyMMddHHmmss");

        String string = format.format(new Date());
        System.out.println(string);

        List<CartItem> cartItems = cartItemMapper.selectByUserId(userid);
        for (CartItem c : cartItems) {
            Orders order = new Orders();
            order.setOrderid(string);
            order.setProname(c.getProname());
            order.setPicture(c.getPicture());
            order.setPriceid(c.getPriceid());
            order.setNum(c.getNum());
            order.setCount(c.getCount());
            ordersMapper.insert(order);
            cartItemMapper.deleteByPrimaryKey(c.getId());
        }
        Ding ding = new Ding();
        ding.setUserid(userid);
        ding.setOrderid(string);
        int res = dingMapper.insert(ding);
        return res;
    }




    // 根据id获得cartItem
    public CartItem selectByPrimaryKey(Integer id) {
        CartItem item = cartItemMapper.selectByPrimaryKey(id);
        return item;
    }


}
