package com.lanou.model;

public class CartItem {
    private Integer id;

    private String proname;

    private String guige;

    private Double price;

    private Integer num;

    private Double count;

    private Integer proid;

    private Integer userid;

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

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige == null ? null : guige.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", proname='" + proname + '\'' +
                ", guige='" + guige + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", count=" + count +
                ", proid=" + proid +
                ", userid=" + userid +
                '}';
    }
}