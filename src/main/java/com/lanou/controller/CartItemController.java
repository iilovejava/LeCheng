package com.lanou.controller;

import com.lanou.dao.ShopCartMapper;
import com.lanou.model.CartItem;

import com.lanou.model.Indent;

import com.lanou.model.ShopCart;

import com.lanou.service.CartItemService;
import com.lanou.service.IndentService;
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
    private IndentService indentService;

    @Autowired
    private ShopCartMapper shopCartMapper;

    // 查看购物车
    @ResponseBody
    @RequestMapping(value = "find")
    public ShopCart selectCart(Integer userid) {
        ShopCart shopCart = shopCartMapper.selectCart(userid);
        List<CartItem> items = cartItemService.selectByUserId(userid);
        Double count = 0D;
        for (CartItem item : items) {
            count += item.getCount();
        }
        shopCart.setCount(count);
        shopCart.setItems(items);
        return shopCart;
    }

    // 添加商品
    @ResponseBody
    @RequestMapping(value = "add")
    public ServiceResponse addCartItem(String proname,Double price,Integer num,Double count,Integer proid,Integer userid) {
        List<CartItem> items = cartItemService.selectByUserId(userid);
        // 遍利购物车
        for (CartItem item : items) {
            if (item.getProid().equals(proid)) {
                // 商品已在购物车 数量相加
                item.setNum(item.getNum() + num);
                int n = item.getNum();
                item.setCount(item.getPrice() * n);
                // 更改数据库中商品数量和小计
                int res = cartItemService.updateItem(item);
                if (res != 1) {
                    return ServiceResponse.createError(1,"添加购物车失败");
                }
                return ServiceResponse.createSuccess("添加成功");
            }
        }
        CartItem newItem = new CartItem();
        newItem.setProname(proname);
        //newItem.setGuige(guige);
        newItem.setPrice(price);
        newItem.setNum(num);
        newItem.setCount(count);
        newItem.setProid(proid);
        newItem.setUserid(userid);
        // 添加到购物车
        int res = cartItemService.insert(newItem);
        if(res == 1) {
            ServiceResponse serviceResponse = ServiceResponse.createSuccess("添加购物车成功");
            return serviceResponse;
        } else {
            ServiceResponse serviceResponse = ServiceResponse.createError(1,"添加失败");
            return serviceResponse;
        }
    }

    @ResponseBody
    @RequestMapping(value = "adaddIndent")
    public ServiceResponse addIndent(Indent indent) {
        int i = indentService.addIndent(indent);
        if(i == 1) {
            return  ServiceResponse.createSuccess("添加订单成功");
        } else {
            return ServiceResponse.createError(1,"添加失败");
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
    public ServiceResponse deleteItem(Integer userid,Integer proid) {

        CartItem cartItem = new CartItem();
        cartItem.setUserid(userid);
        cartItem.setProid(proid);
        CartItem item = cartItemService.selectItemByUidAndPid(cartItem);
        // 根据id移除item
        int res = cartItemService.deleteItemById(item.getId());
        if (res != 1) {
            return ServiceResponse.createError(1,"商品移除失败");
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
        if (items == null) {
            return ServiceResponse.createError(1,"清空失败");
        } else {
            return ServiceResponse.createSuccess("清空购物车成功");
        }
    }

}
