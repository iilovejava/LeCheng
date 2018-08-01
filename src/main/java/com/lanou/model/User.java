package com.lanou.model;

public class User {
    private Integer userid;

    private String userphone;

    private String userpassword;

    private String username;

    private Integer usergender;

    private String userbirthday;

    private Integer userqq;

    private String useraddress;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getUsergender() {
        return usergender;
    }

    public void setUsergender(Integer usergender) {
        this.usergender = usergender;
    }

    public String getUserbirthday() {
        return userbirthday;
    }

    public void setUserbirthday(String userbirthday) {
        this.userbirthday = userbirthday == null ? null : userbirthday.trim();
    }

    public Integer getUserqq() {
        return userqq;
    }

    public void setUserqq(Integer userqq) {
        this.userqq = userqq;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress == null ? null : useraddress.trim();
    }
}