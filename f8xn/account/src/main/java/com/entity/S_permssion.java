package com.entity;

public class S_permssion {
    private Integer permssionid;

    private String name;

    private Integer permssion;

    public Integer getPermssionid() {
        return permssionid;
    }

    public void setPermssionid(Integer permssionid) {
        this.permssionid = permssionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPermssion() {
        return permssion;
    }

    public void setPermssion(Integer permssion) {
        this.permssion = permssion;
    }
}