package com.hu.lottery.bean;

/**
 * Created by huqiucheng on 2017/10/31 0031.
 */

public class HistoryLottery {
    private String pdi;
    private String atime;
    private String acode;
    private String trycode;

    public String getPdi() {
        return pdi;
    }

    public void setPdi(String pdi) {
        this.pdi = pdi;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getAcode() {
        return acode;
    }

    public void setAcode(String acode) {
        this.acode = acode;
    }

    public String getTrycode() {
        return trycode;
    }

    public void setTrycode(String trycode) {
        this.trycode = trycode;
    }

    @Override
    public String toString() {
        return "HistoryLottery{" +
                "pdi='" + pdi + '\'' +
                ", atime='" + atime + '\'' +
                ", acode='" + acode + '\'' +
                ", trycode='" + trycode + '\'' +
                '}';
    }
}
