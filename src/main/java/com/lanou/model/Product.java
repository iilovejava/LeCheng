package com.lanou.model;

import java.util.List;

public class Product {
    private Integer productid;

    private String productname;

    private String des;

    private String producturl;

    private String productdes;

    private Integer cateid;

    private String cate;

    private List<Attr> attrs;

    private List<Sale> sales;

    private Price price;

    private List<Picture> pictures;

    private List<XqPicture> xqPictures;

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public List<XqPicture> getXqPictures() {
        return xqPictures;
    }

    public void setXqPictures(List<XqPicture> xqPictures) {
        this.xqPictures = xqPictures;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Attr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Attr> attrs) {
        this.attrs = attrs;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public String getProducturl() {
        return producturl;
    }

    public void setProducturl(String producturl) {
        this.producturl = producturl == null ? null : producturl.trim();
    }

    public String getProductdes() {
        return productdes;
    }

    public void setProductdes(String productdes) {
        this.productdes = productdes == null ? null : productdes.trim();
    }

    public Integer getCateid() {
        return cateid;
    }

    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productid=" + productid +
                ", productname='" + productname + '\'' +
                ", des='" + des + '\'' +
                ", producturl='" + producturl + '\'' +
                ", productdes='" + productdes + '\'' +
                ", cateid=" + cateid +
                ", cate='" + cate + '\'' +
                ", attrs=" + attrs +
                ", sales=" + sales +
                ", price=" + price +
                ", pictures=" + pictures +
                ", xqPictures=" + xqPictures +
                '}';
    }
}