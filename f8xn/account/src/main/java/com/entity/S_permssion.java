package com.entity;

public class S_permssion {
    private Integer permssionid;

    private String spname;

    private Integer permssion;

    public Integer getPermssionid() {
        return permssionid;
    }

    public void setPermssionid(Integer permssionid) {
        this.permssionid = permssionid;
    }

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname == null ? null : spname.trim();
    }

    public Integer getPermssion() {
        return permssion;
    }

    public void setPermssion(Integer permssion) {
        this.permssion = permssion;
    }
}