package com.entity;

import java.util.Date;

public class Accountlist {
    private Long accountid;

    private Integer account;

    private String password;

    private Date regdate;

    private Long telphone;

    private String paycard;

    private String aother1;

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Long getTelphone() {
        return telphone;
    }

    public void setTelphone(Long telphone) {
        this.telphone = telphone;
    }

    public String getPaycard() {
        return paycard;
    }

    public void setPaycard(String paycard) {
        this.paycard = paycard == null ? null : paycard.trim();
    }

    public String getAother1() {
        return aother1;
    }

    public void setAother1(String aother1) {
        this.aother1 = aother1 == null ? null : aother1.trim();
    }
}