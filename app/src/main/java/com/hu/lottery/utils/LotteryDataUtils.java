package com.hu.lottery.utils;

import com.hu.lottery.Constances.LotteryConstance;
import com.hu.lottery.ui.ChooseBallActivity;

import java.util.Arrays;

/**
 * Created by huqiucheng on 2017/10/31 0031.
 */

public class LotteryDataUtils {

    private static LotteryDataUtils lotteryDataUtils;

    private LotteryDataUtils() {

    }

    public static LotteryDataUtils getInstance() {
        if (null == lotteryDataUtils) {
            lotteryDataUtils = new LotteryDataUtils();
        }
        return lotteryDataUtils;
    }

    public int[] getSsqData() {
        int[] data = new int[7];
        int a, b, c, d, e, f, z;
        a = randomSsqRedBall();
        data[0] = a;
        b = randomSsqRedBall();
        while (b == a) {
            b = randomSsqRedBall();
        }
        data[1] = b;
        c = randomSsqRedBall();
        while (c == a || c == b) {
            c = randomSsqRedBall();
        }
        data[2] = c;

        d = randomSsqRedBall();
        while (d == a || d == b || d == c) {
            d = randomSsqRedBall();
        }
        data[3] = d;

        e = randomSsqRedBall();
        while (e == a || e == b || e == c || e == d) {
            e = randomSsqRedBall();
        }
        data[4] = e;
        f = randomSsqRedBall();
        while (f == a || f == b || f == c || f == d || f == e) {
            f = randomSsqRedBall();
        }
        data[5] = f;
        z = randomSsqBlueBall();
        data[6] = z;
        Arrays.sort(data, 0, 6);
        return data;
    }

    public int[] getDltData() {
        int[] data = new int[7];
        int a, b, c, d, e, x, z;
        a = randomDltRedBall();
        data[0] = a;
        b = randomDltRedBall();
        while (b == a) {
            b = randomDltRedBall();
        }
        data[1] = b;
        c = randomDltRedBall();
        while (c == a || c == b) {
            c = randomDltRedBall();
        }
        data[2] = c;

        d = randomDltRedBall();
        while (d == a || d == b || d == c) {
            d = randomDltRedBall();
        }
        data[3] = d;

        e = randomDltRedBall();
        while (e == a || e == b || e == c || e == d) {
            e = randomDltRedBall();
        }
        data[4] = e;

        x = randomDltBlueBall();
        data[5] = x;
        z = randomDltBlueBall();
        while (z == x) {
            z = randomDltBlueBall();
        }
        data[6] = z;
        Arrays.sort(data, 0, 5);
        if (data[data.length - 1] < data[data.length - 2]) {
            int temp = data[data.length - 1];
            data[data.length - 1] = data[data.length - 2];
            data[data.length - 2] = temp;
        }
        return data;
    }

    public String getLotterysString(int type) {
        StringBuilder result = new StringBuilder();
        if (type == LotteryConstance.TYPE_SHUANGSEQIU) {//1-33,1-16(6+1)
            int[] data = LotteryDataUtils.getInstance().getSsqData();
            for (int i = 0; i < data.length; i++) {
                if (i > 0 && i < data.length - 1) {
                    result.append(" , ");
                } else if (i == data.length - 1) {
                    result.append(" | ");
                }
                result.append(formatString(data[i]));
            }
        } else if (type == LotteryConstance.TYPE_DALETOU) {//1-35,1-12(5+2)
            int[] data = LotteryDataUtils.getInstance().getDltData();
            for (int i = 0; i < data.length; i++) {
                if (i > 0 && i < data.length - 2 || i == data.length - 1) {
                    result.append(" , ");
                } else if (i == data.length - 2) {
                    result.append(" | ");
                }
                result.append(formatString(data[i]));
            }
        }
        return result.toString();
    }

    public String getLotterysString(int type, int[] data) {
        StringBuilder result = new StringBuilder();
        if (type == LotteryConstance.TYPE_SHUANGSEQIU) {//1-33,1-16(6+1)
            for (int i = 0; i < data.length; i++) {
                if (i > 0 && i < data.length - 1) {
                    result.append(" , ");
                } else if (i == data.length - 1) {
                    result.append(" | ");
                }
                result.append(formatString(data[i]));
            }
        } else if (type == LotteryConstance.TYPE_DALETOU) {//1-35,1-12(5+2)
            for (int i = 0; i < data.length; i++) {
                if (i > 0 && i < data.length - 2 || i == data.length - 1) {
                    result.append(" , ");
                } else if (i == data.length - 2) {
                    result.append(" | ");
                }
                result.append(formatString(data[i]));
            }
        }
        return result.toString();
    }

    private int randomSsqRedBall() {
        return (int) (Math.random() * (33 - 1) + 1);
    }

    private int randomSsqBlueBall() {
        return (int) (Math.random() * (16 - 1) + 1);
    }

    private int randomDltRedBall() {
        return (int) (Math.random() * (35 - 1) + 1);
    }

    private int randomDltBlueBall() {
        return (int) (Math.random() * (12 - 1) + 1);
    }

    public String formatString(int str) {
        return String.format("%02d", str);
    }
}
