package com.hu.lottery.bean;

import java.util.List;

/**
 * Created by huqiucheng on 2017/10/31 0031.
 */

public class LostLottery {

    private String pid;
    private String atime;
    private String gpool;
    private String tn;
    private String periods;
    private String trycode;
    private List<HistoryLottery> historyLotteryList;
    private List<LostBall> ballList;


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getGpool() {
        return gpool;
    }

    public void setGpool(String gpool) {
        this.gpool = gpool;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public String getTrycode() {
        return trycode;
    }

    public void setTrycode(String trycode) {
        this.trycode = trycode;
    }

    public List<HistoryLottery> getHistoryLotteryList() {
        return historyLotteryList;
    }

    public void setHistoryLotteryList(List<HistoryLottery> historyLotteryList) {
        this.historyLotteryList = historyLotteryList;
    }

    public List<LostBall> getBallList() {
        return ballList;
    }

    public void setBallList(List<LostBall> ballList) {
        this.ballList = ballList;
    }

    @Override
    public String toString() {
        return "LostLottery{" +
                "pid='" + pid + '\'' +
                ", atime='" + atime + '\'' +
                ", gpool='" + gpool + '\'' +
                ", tn='" + tn + '\'' +
                ", periods='" + periods + '\'' +
                ", trycode='" + trycode + '\'' +
                ", historyLotteryList=" + historyLotteryList +
                ", ballList=" + ballList +
                '}';
    }
}
