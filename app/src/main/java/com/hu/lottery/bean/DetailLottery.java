package com.hu.lottery.bean;

/**
 * Created by huqiucheng on 2017/10/31 0031.
 */

public class DetailLottery {

    private String gid;
    private String pid;
    private String acode;
    private String code;
    private String gsale;
    private String ginfo;
    private String ninfo;
    private String gpool;
    private String atime;


    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAcode() {
        return acode;
    }

    public void setAcode(String acode) {
        this.acode = acode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGsale() {
        return gsale;
    }

    public void setGsale(String gsale) {
        this.gsale = gsale;
    }

    public String getGinfo() {
        return ginfo;
    }

    public void setGinfo(String ginfo) {
        this.ginfo = ginfo;
    }

    public String getNinfo() {
        return ninfo;
    }

    public void setNinfo(String ninfo) {
        this.ninfo = ninfo;
    }

    public String getGpool() {
        return gpool;
    }

    public void setGpool(String gpool) {
        this.gpool = gpool;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    @Override
    public String toString() {
        return "DetailLottery{" +
                "gid='" + gid + '\'' +
                ", pid='" + pid + '\'' +
                ", acode='" + acode + '\'' +
                ", code='" + code + '\'' +
                ", gsale='" + gsale + '\'' +
                ", ginfo='" + ginfo + '\'' +
                ", ninfo='" + ninfo + '\'' +
                ", gpool='" + gpool + '\'' +
                ", atime='" + atime + '\'' +
                '}';
    }
}
