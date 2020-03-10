package com.entity;

public class T_flatweapon_correspond {
    private Integer wFlatId;

    private Integer wWeaponId;

    private Integer wWeaponType;

    private String wWeaponName;

    private Integer wInfoCampaignF;

    public Integer getwFlatId() {
        return wFlatId;
    }

    public void setwFlatId(Integer wFlatId) {
        this.wFlatId = wFlatId;
    }

    public Integer getwWeaponId() {
        return wWeaponId;
    }

    public void setwWeaponId(Integer wWeaponId) {
        this.wWeaponId = wWeaponId;
    }

    public Integer getwWeaponType() {
        return wWeaponType;
    }

    public void setwWeaponType(Integer wWeaponType) {
        this.wWeaponType = wWeaponType;
    }

    public String getwWeaponName() {
        return wWeaponName;
    }

    public void setwWeaponName(String wWeaponName) {
        this.wWeaponName = wWeaponName == null ? null : wWeaponName.trim();
    }

    public Integer getwInfoCampaignF() {
        return wInfoCampaignF;
    }

    public void setwInfoCampaignF(Integer wInfoCampaignF) {
        this.wInfoCampaignF = wInfoCampaignF;
    }
}