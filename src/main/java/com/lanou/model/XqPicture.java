package com.lanou.model;

public class XqPicture {
    private Integer id;

    private String xqurl;

    private Integer proid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXqurl() {
        return xqurl;
    }

    public void setXqurl(String xqurl) {
        this.xqurl = xqurl == null ? null : xqurl.trim();
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }
}