package com.entity;

import java.util.Date;

public class Credit {
    private Integer credit;

    private Date date;

    private Long value;

    private String cother1;

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getCother1() {
        return cother1;
    }

    public void setCother1(String cother1) {
        this.cother1 = cother1 == null ? null : cother1.trim();
    }
}