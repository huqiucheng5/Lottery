package com.hu.lottery.utils;

import android.util.Log;

/**
 * Created by huqiucheng on 2017/10/31 0031.
 */

public class LogUtis {
    private static final String TAG = "Lottery";
    private static final boolean DEBUG = true;

    public static void logi(String log) {
        if (DEBUG) {
            Log.i(TAG, log);
        }
    }

    public static void loge(String log) {
        if (DEBUG) {
            Log.e(TAG, log);
        }
    }
}
