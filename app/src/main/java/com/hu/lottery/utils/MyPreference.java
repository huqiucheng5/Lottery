package com.hu.lottery.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by huqiucheng on 2017/9/19 0019.
 */

public class MyPreference {

    private static MyPreference myPreference;
    private static SharedPreferences sharedPreferences;

    private MyPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("Lottery_SP", Context.MODE_PRIVATE);
    }

    public static MyPreference getInstance(Context context) {
        if (null == myPreference) {
            myPreference = new MyPreference(context);
        }
        return myPreference;
    }

    public void putInt(String key, int values) {
        sharedPreferences.edit().putInt(key, values).commit();
    }

    public void putString(String key, String values) {
        sharedPreferences.edit().putString(key, values).commit();
    }

    public void putBoolean(String key, boolean values) {
        sharedPreferences.edit().putBoolean(key, values).commit();
    }

    public int getInt(String key, int defaultValues) {
        return sharedPreferences.getInt(key, defaultValues);
    }

    public String getString(String key, String defaultValues) {
        return sharedPreferences.getString(key, defaultValues);
    }

    public boolean getBoolean(String key, boolean defaultValues) {
        return sharedPreferences.getBoolean(key, defaultValues);
    }


    public void onDestory() {
        if (null != sharedPreferences) sharedPreferences = null;
        if (null != myPreference) myPreference = null;
    }
}
