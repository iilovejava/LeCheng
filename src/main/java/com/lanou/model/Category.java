package com.lanou.model;

import java.util.List;

public class Category {
    private Integer categoryid;

    private String categoryname;

    //private List<Attr> attrs;

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

//    public List<Attr> getAttrs() {
//        return attrs;
//    }

//    public void setAttrs(List<Attr> attrs) {
//        this.attrs = attrs;
//    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryid=" + categoryid +
                ", categoryname='" + categoryname + '\'' +
                ", products=" + products +
                '}';
    }
}