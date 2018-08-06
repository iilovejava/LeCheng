package com.lanou.model;


public class Collect {
    private Integer collectid;

    private Integer userid;

    private Integer productid;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCollectid() {
        return collectid;
    }

    public void setCollectid(Integer collectid) {
        this.collectid = collectid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "collectid=" + collectid +
                ", userid=" + userid +
                ", productid=" + productid +
                ", product=" + product +
                '}';
    }
}