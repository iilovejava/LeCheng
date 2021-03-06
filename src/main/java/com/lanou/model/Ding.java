package com.lanou.model;

import java.util.List;

public class Ding {
    private Integer id;

    private Integer userid;

    private String orderid;

    private Double count;

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private List<Orders> orders;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getOrderid() {
        return orderid;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    @Override
    public String toString() {
        return "Ding{" +
                "id=" + id +
                ", userid=" + userid +
                ", orderid='" + orderid + '\'' +
                ", count=" + count +
                ", state='" + state + '\'' +
                ", orders=" + orders +
                '}';
    }
}