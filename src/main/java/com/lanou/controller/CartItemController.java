package com.lanou.controller;

import com.lanou.model.CartItem;
import com.lanou.model.Indent;
import com.lanou.service.CartItemService;
import com.lanou.service.IndentService;
import com.lanou.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
@RequestMapping(value = "cart")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private IndentService indentService;

    @ResponseBody
    @RequestMapping(value = "add")
    public ServiceResponse addCartItem(CartItem cartItem) {

        int res = cartItemService.addCartItem(cartItem);
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


}
