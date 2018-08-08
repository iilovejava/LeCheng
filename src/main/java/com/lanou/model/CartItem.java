package com.lanou.model;

public class CartItem {
    private Integer id;

    private String proname;

    private String picture;

    private Integer priceid;

    private Integer num;

    private Double count;

    private Integer userid;

    private Double unitPrice;

    private String norms;

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

<<<<<<< HEAD
=======

>>>>>>> d04a27beab9b55c0616597743fe6512207420dd6
    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", proname='" + proname + '\'' +
                ", picture='" + picture + '\'' +
                ", priceid=" + priceid +
                ", num=" + num +
                ", count=" + count +
                ", userid=" + userid +
                ", unitPrice=" + unitPrice +
                ", norms='" + norms + '\'' +
                '}';
    }
<<<<<<< HEAD
=======

>>>>>>> d04a27beab9b55c0616597743fe6512207420dd6
}