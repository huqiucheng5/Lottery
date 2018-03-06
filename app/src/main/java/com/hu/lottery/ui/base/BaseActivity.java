package com.hu.lottery.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.hu.lottery.utils.LogUtis;

/**
 * Created by huqiucheng on 2017/11/2 0002.
 */

public class BaseActivity extends AppCompatActivity {

    protected void logi(String str){
        LogUtis.logi(str);
    }
    protected void loge(String str){
        LogUtis.loge(str);
    }
}
