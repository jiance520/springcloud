package com.entity;

import java.util.Date;

public class Income {
    private Integer incomeid;

    private Float income;

    private Date date;

    private String iother1;

    public Integer getIncomeid() {
        return incomeid;
    }

    public void setIncomeid(Integer incomeid) {
        this.incomeid = incomeid;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIother1() {
        return iother1;
    }

    public void setIother1(String iother1) {
        this.iother1 = iother1 == null ? null : iother1.trim();
    }
}