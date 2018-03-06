package com.hu.lottery.bean;

/**
 * Created by huqiucheng on 2017/10/31 0031.
 */

public class LostBall {
    private String color;
    private String curyl;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCuryl() {
        return curyl;
    }

    public void setCuryl(String curyl) {
        this.curyl = curyl;
    }

    @Override
    public String toString() {
        return "LostBall{" +
                "color='" + color + '\'' +
                ", curyl='" + curyl + '\'' +
                '}';
    }
}
