package com.lanou.model;

public class Address {
    private Integer id;

    private Integer userid;

    private String provinceid;

    private String cityid;

    private String areaid;

    private String ssq;

    private String xq;

    private String consignee;

    private String tel;

    private String moren;

    public String getSsq() {
        return ssq;
    }

    public void setSsq(String ssq) {
        this.ssq = ssq;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid == null ? null : provinceid.trim();
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq == null ? null : xq.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getMoren() {
        return moren;
    }

    public void setMoren(String moren) {
        this.moren = moren == null ? null : moren.trim();
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userid=" + userid +
                ", provinceid='" + provinceid + '\'' +
                ", cityid='" + cityid + '\'' +
                ", areaid='" + areaid + '\'' +
                ", ssq='" + ssq + '\'' +
                ", xq='" + xq + '\'' +
                ", consignee='" + consignee + '\'' +
                ", tel='" + tel + '\'' +
                ", moren='" + moren + '\'' +
                '}';
    }
}