package com.entity;

import java.util.Date;

public class Incomelist {
    private Integer incomeid;

    private Integer userid;

    private Float imoney;

    private Date idate;

    private String iitem;

    private String account;

    private String iother1;

    public Integer getIncomeid() {
        return incomeid;
    }

    public void setIncomeid(Integer incomeid) {
        this.incomeid = incomeid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Float getImoney() {
        return imoney;
    }

    public void setImoney(Float imoney) {
        this.imoney = imoney;
    }

    public Date getIdate() {
        return idate;
    }

    public void setIdate(Date idate) {
        this.idate = idate;
    }

    public String getIitem() {
        return iitem;
    }

    public void setIitem(String iitem) {
        this.iitem = iitem == null ? null : iitem.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getIother1() {
        return iother1;
    }

    public void setIother1(String iother1) {
        this.iother1 = iother1 == null ? null : iother1.trim();
    }
}