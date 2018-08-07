package com.lanou.model;

public class Price {
    private Integer priceid;

    private Integer one;

    private Integer two;

    private Integer three;

    private Double price;

    private Integer proid;

    public Integer getPriceid() {
        return priceid;
    }

    public void setPriceid(Integer priceid) {
        this.priceid = priceid;
    }

    public Integer getOne() {
        return one;
    }

    public void setOne(Integer one) {
        this.one = one;
    }

    public Integer getTwo() {
        return two;
    }

    public void setTwo(Integer two) {
        this.two = two;
    }

    public Integer getThree() {
        return three;
    }

    public void setThree(Integer three) {
        this.three = three;
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

    @Override
    public String toString() {
        return "Price{" +
                "priceid=" + priceid +
                ", one=" + one +
                ", two=" + two +
                ", three=" + three +
                ", price=" + price +
                ", proid=" + proid +
                '}';
    }
}