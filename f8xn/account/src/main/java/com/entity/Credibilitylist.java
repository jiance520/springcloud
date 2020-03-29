package com.entity;

import java.util.Date;

public class Credibilitylist {
    private Integer creditid;

    private Integer userid;

    private Integer creditscore;

    private Date cdate;

    private String citem;

    private String cother1;

    public Integer getCreditid() {
        return creditid;
    }

    public void setCreditid(Integer creditid) {
        this.creditid = creditid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCreditscore() {
        return creditscore;
    }

    public void setCreditscore(Integer creditscore) {
        this.creditscore = creditscore;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getCitem() {
        return citem;
    }

    public void setCitem(String citem) {
        this.citem = citem == null ? null : citem.trim();
    }

    public String getCother1() {
        return cother1;
    }

    public void setCother1(String cother1) {
        this.cother1 = cother1 == null ? null : cother1.trim();
    }
}