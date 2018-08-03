package com.lanou.model;

import java.util.List;

public class Index {
    private Integer id;

    private String name;

    private String des;

    private String picture;

    private Integer type;

    private Integer parentid;

    private Integer proid;

    private List<Index> childrenIndex;

    public List<Index> getChildrenIndex() {
        return childrenIndex;
    }

    public void setChildrenIndex(List<Index> childrenIndex) {
        this.childrenIndex = childrenIndex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    @Override
    public String toString() {
        return "Index{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", des='" + des + '\'' +
                ", picture='" + picture + '\'' +
                ", type=" + type +
                ", parentid=" + parentid +
                ", proid=" + proid +
                ", childrenIndex=" + childrenIndex +
                '}';
    }
}