package com.lanou.model;

public class Price {
    private Integer priceid;

    private String one;

    private String two;

    private String three;

    private Double price;

    private Integer proid;

    public Integer getPriceid() {
        return priceid;
    }

    public void setPriceid(Integer priceid) {
        this.priceid = priceid;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one == null ? null : one.trim();
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two == null ? null : two.trim();
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three == null ? null : three.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }
}