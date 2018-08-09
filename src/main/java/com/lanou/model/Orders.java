package com.lanou.model;

public class Orders {
    private Integer id;

    private String orderid;

    private String proname;

    private String picture;

    private Integer priceid;

    private Integer num;

    private Double count;

    private Double unitPrice;

    private String norms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname == null ? null : proname.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Integer getPriceid() {
        return priceid;
    }

    public void setPriceid(Integer priceid) {
        this.priceid = priceid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderid='" + orderid + '\'' +
                ", proname='" + proname + '\'' +
                ", picture='" + picture + '\'' +
                ", priceid=" + priceid +
                ", num=" + num +
                ", count=" + count +
                ", unitPrice=" + unitPrice +
                ", norms='" + norms + '\'' +
                '}';
    }
}