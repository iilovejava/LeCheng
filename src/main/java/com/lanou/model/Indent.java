package com.lanou.model;

public class Indent {
    private Integer id;

    private Integer indentid;

    private Integer userid;

    private Integer priceid;

    private Integer num;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndentid() {
        return indentid;
    }

    public void setIndentid(Integer indentid) {
        this.indentid = indentid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Indent{" +
                "id=" + id +
                ", indentid=" + indentid +
                ", userid=" + userid +
                ", priceid=" + priceid +
                ", num=" + num +
                ", state=" + state +
                '}';
    }
}