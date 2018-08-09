package com.lanou.controller;


import com.lanou.dao.CartItemMapper;

import com.lanou.dao.*;
import com.lanou.model.*;

import com.lanou.dao.ShopCartMapper;
import com.lanou.model.CartItem;
import com.lanou.model.ShopCart;

import com.lanou.service.CartItemService;
import com.lanou.service.DingService;
import com.lanou.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        ShopCart shopCart = shopCartMapper.selectCart(userid);
        List<CartItem> items = cartItemService.selectByUserId(userid);
        Double amount = 0D;
        for (CartItem item : items) {
            // 获得单价
            item.setUnitPrice(item.getCount() / item.getNum());
            // 获得描述
            Price price = priceMapper.findPriceBypriId(item.getPriceid());
            String one = valueMapper.selectByPrimaryKey(price.getOne()).getValue();
            String two = valueMapper.selectByPrimaryKey(price.getTwo()).getValue();
            String three = valueMapper.selectByPrimaryKey(price.getThree()).getValue();
            item.setNorms(one + " " + two + " " + three);
            // 总计
            amount += item.getCount();
        }
        shopCart.setCount(amount);
        shopCart.setItems(items);
        return shopCart;
    }

    // 购物车转换成订单
    @ResponseBody
    @RequestMapping(value = "ding")
    public Ding addding(Integer userid) {
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
        }

        Ding ding = new Ding();
        ding.setUserid(userid);
        ding.setOrderid(string);
        dingMapper.insert(ding);

        deleteAll(userid);
        return ding;
    }
    // 订单 购买单个商品
    @ResponseBody
    @RequestMapping(value = "buy")
    public Ding buyding(Orders order) {
        Format format = new SimpleDateFormat("yyyyMMddHHmmss");
        String string = format.format(new Date());
        order.setOrderid(string);
        int insert = ordersMapper.insert(order);
        return null;
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

    // 添加商品
    @ResponseBody
    @RequestMapping(value = "add")
    public ServiceResponse addCartItem(Integer priceid, Integer num, Integer userid) {
        Price price = priceMapper.findPriceBypriId(priceid);
        // 根据userId遍历购物车
        List<CartItem> items = cartItemService.selectByUserId(userid);
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
                int res = cartItemService.updateItem(item);
                if (res != 1) {
                    return ServiceResponse.createError(1, "添加购物车失败");
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


    // 修改商品数量
//    @ResponseBody
//    @RequestMapping(value = "jia")
//    public ServiceResponse updateItem(Integer userid,Integer proid,Integer num) {
//        CartItem item = cartItemService.selectItem(cartItem);
//        int res = cartItemService.updateItem(item.getNum());
//        if (res != 1) {
//            return ServiceResponse.createError(1,"商品数量修改失败");
//        }
//        return ServiceResponse.createSuccess("数量修改成功");
//    }


    // 移除商品
    @ResponseBody
    @RequestMapping(value = "delete")
    public ServiceResponse deleteItem(Integer userid, Integer priceid) {
        CartItem cartItem = new CartItem();
        cartItem.setUserid(userid);
        cartItem.setPriceid(priceid);
        CartItem item = cartItemService.findItemByproIdAndpriId(cartItem);
        // 根据id移除item
        int res = cartItemService.deleteItemById(item.getId());
        if (res != 1) {
            return ServiceResponse.createError(1, "商品移除失败");
        }
        return ServiceResponse.createSuccess("移除成功");
    }

    // 清空购物车
    @ResponseBody
    @RequestMapping(value = "deleteAll")
    public ServiceResponse deleteAll(Integer userid) {
        List<CartItem> items = cartItemService.selectByUserId(userid);
        for (CartItem item : items) {
            cartItemService.deleteItemById(item.getId());
        }
        if (items.size() != 0) {
            return ServiceResponse.createError(1, "清空失败");
        } else {
            return ServiceResponse.createSuccess("清空购物车成功");
        }
    }


////    <select id="findByOrderid" resultType="Order" parameterType="int" >
//    select * from order
//    where orderid = #{orderid}
//  </select>
    //List<Order> findByOrderid(String orderid);
}
