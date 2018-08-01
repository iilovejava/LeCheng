package com.lanou.model;

import java.util.List;

public class Attr {
    private Integer id;

    private String attrname;

    private Integer cateid;

    private List<Value> values;

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttrname() {
        return attrname;
    }

    public void setAttrname(String attrname) {
        this.attrname = attrname == null ? null : attrname.trim();
    }

    public Integer getCateid() {
        return cateid;
    }

    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    @Override
    public String toString() {
        return "Attr{" +
                "id=" + id +
                ", attrname='" + attrname + '\'' +
                ", cateid=" + cateid +
                ", values=" + values +
                '}';
    }
}