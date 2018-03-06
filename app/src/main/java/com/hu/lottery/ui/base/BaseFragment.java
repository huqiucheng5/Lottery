package com.hu.lottery.ui.base;

import android.app.Fragment;

import com.hu.lottery.utils.LogUtis;

/**
 * Created by huqiucheng on 2017/11/2 0002.
 */

public class BaseFragment extends Fragment {

    protected void logi(String str) {
        LogUtis.logi(str);
    }

    protected void loge(String str) {
        LogUtis.loge(str);
    }

}
