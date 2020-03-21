package com.entity;

import java.util.Date;

public class Course {
    private Integer sid;

    private String stitle;

    private Long snumman;

    private Float sscore;

    private Float sprice;

    private Float sprice2;

    private String shuodong;

    private Integer stime;

    private String sauthor;

    private String sdetail;

    private Date sdate;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle == null ? null : stitle.trim();
    }

    public Long getSnumman() {
        return snumman;
    }

    public void setSnumman(Long snumman) {
        this.snumman = snumman;
    }

    public Float getSscore() {
        return sscore;
    }

    public void setSscore(Float sscore) {
        this.sscore = sscore;
    }

    public Float getSprice() {
        return sprice;
    }

    public void setSprice(Float sprice) {
        this.sprice = sprice;
    }

    public Float getSprice2() {
        return sprice2;
    }

    public void setSprice2(Float sprice2) {
        this.sprice2 = sprice2;
    }

    public String getShuodong() {
        return shuodong;
    }

    public void setShuodong(String shuodong) {
        this.shuodong = shuodong == null ? null : shuodong.trim();
    }

    public Integer getStime() {
        return stime;
    }

    public void setStime(Integer stime) {
        this.stime = stime;
    }

    public String getSauthor() {
        return sauthor;
    }

    public void setSauthor(String sauthor) {
        this.sauthor = sauthor == null ? null : sauthor.trim();
    }

    public String getSdetail() {
        return sdetail;
    }

    public void setSdetail(String sdetail) {
        this.sdetail = sdetail == null ? null : sdetail.trim();
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "sid=" + sid +
                ", stitle='" + stitle + '\'' +
                ", snumman=" + snumman +
                ", sscore=" + sscore +
                ", sprice=" + sprice +
                ", sprice2=" + sprice2 +
                ", shuodong='" + shuodong + '\'' +
                ", stime=" + stime +
                ", sauthor='" + sauthor + '\'' +
                ", sdetail='" + sdetail + '\'' +
                ", sdate=" + sdate +
                '}';
    }
}