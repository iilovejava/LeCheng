package com.lanou.controller;


import com.lanou.dao.OrdersMapper;
import com.lanou.dao.CartItemMapper;

import com.lanou.dao.*;
import com.lanou.model.*;

import com.lanou.dao.ShopCartMapper;
import com.lanou.model.CartItem;

import com.lanou.model.ShopCart;

import com.lanou.service.CartItemService;
import com.lanou.model.ShopCart;

import com.lanou.service.CartItemService;
import com.lanou.service.DingService;
import com.lanou.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "cart")

public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private DingService dingService;

    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private DingMapper dingMapper;
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
        ShopCart shopCart = cartItemService.selectByUserId(userid);
        return shopCart;
    }


    // 添加商品
    @ResponseBody
    @RequestMapping(value = "add")
    public ServiceResponse addCartItem (Integer priceid, Integer num, Integer userid){
        int res = cartItemService.insert(priceid,num,userid);

        if (res == 1) {
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
            ServiceResponse serviceResponse = ServiceResponse.createError(1, "添加失败");
            return serviceResponse;
        }
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
        List<CartItem> items = cartItemService.deleteAll(userid);
        if (items.size() != 0) {
            return ServiceResponse.createError(1,"清空购物车失败");
        } else {
            return ServiceResponse.createSuccess("清空购物车成功");
        }
    }


    // 修改商品数量
    @ResponseBody
    @RequestMapping(value = "num")
    public ServiceResponse updateItem(Integer itemid,Integer num,Double price) {

        int res = cartItemService.updateItem(itemid,num,price);
        if (res == 1) {
            return ServiceResponse.createSuccess("商品数量修改成功");
        }
        return ServiceResponse.createError(1,"商品项修改失败");
    }


    // 购物车转换成订单
    @ResponseBody
    @RequestMapping(value = "ding")
    public ServiceResponse addding(Integer userid) {

        int res = cartItemService.addding(userid);
        if (res != 1) {
            return ServiceResponse.createError(1,"订单生成失败");
        }
        return ServiceResponse.createSuccess("订单生成成功");
    }

    // 订单 购买单个商品
    @ResponseBody
    @RequestMapping(value = "buy")
    public ServiceResponse buyding(String proname, String picture, Double price, Integer num, Double count, Integer userid) {
        Ding ding = new Ding();
        Orders order = new Orders();
        // 时间戳作为订单号
        Format format = new SimpleDateFormat("yyyyMMddHHmmss");
        String string = format.format(new Date());
        order.setOrderid(string);
        order.setProname(proname);
        order.setPicture(picture);
        order.setUnitPrice(price);
        order.setNum(num);
        order.setCount(count);
        ordersMapper.insert(order);
        ding.setOrderid(string);
        ding.setUserid(userid);
        int res = dingMapper.insert(ding);
        if (res != 1) {
            return ServiceResponse.createError(1,"订单生成失败");
        }
        return ServiceResponse.createSuccess("提交订单成功",order);
    }

    // 查看订单
    @ResponseBody
    @RequestMapping(value = "findding")
    public List<Ding> findding(Integer userid) {
        System.out.println(userid);
        List<Ding> dings = dingService.findByUserid(userid);
        Double amount = 0D;
        for (Ding ding : dings) {
            String orderid = ding.getOrderid();
            List<Orders> orders = ordersMapper.findByOrderid(orderid);
            System.out.println(orders);
            for (Orders order : orders) {
                order.setUnitPrice(order.getCount() / order.getNum());
                // 获得描述
                Price price = priceMapper.findPriceBypriId(order.getPriceid());
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
                order.setNorms(one +" " + two + " " + three);

                // 总计
                amount += order.getCount();
            }
            ding.setCount(amount);
            ding.setOrders(orders);
        }
        System.out.println(dings);
        return dings;
    }

    // 取消订单
    @ResponseBody
    @RequestMapping(value = "cancel")
    public ServiceResponse cancel(String orderid) {
        Ding ding = dingMapper.selectDingByorderId(orderid);
        ding.setState("已取消");
        int res = dingMapper.updateState(ding);
        if (res != 1) {
            return ServiceResponse.createError(1,"取消订单失败");
        }
        return  ServiceResponse.createSuccess("订单已取消",ding);
    }

    // 删除订单
    @ResponseBody
    @RequestMapping(value = "deleteDing")
    public ServiceResponse deleteDing(String orderid) {
        ordersMapper.deleteByOrderid(orderid);
        int res = dingMapper.deleteDing(orderid);
        if (res != 1) {
            return ServiceResponse.createError(1,"订单删除失败");
        }
        return ServiceResponse.createSuccess("订单已删除");
    }

}
