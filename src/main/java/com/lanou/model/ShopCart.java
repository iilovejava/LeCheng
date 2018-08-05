package com.lanou.model;

import java.util.List;

public class ShopCart {
    private Integer cartid;

    private Integer userid;

    private Double count;

    private List<CartItem> items;

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Integer getCartid() {
        return cartid;
    }

    public void setCartid(Integer cartid) {
        this.cartid = cartid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ShopCart{" +
                "cartid=" + cartid +
                ", userid=" + userid +
                ", count=" + count +
                ", items=" + items +
                '}';
    }
}