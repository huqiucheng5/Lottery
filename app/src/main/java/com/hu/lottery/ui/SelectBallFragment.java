package com.hu.lottery.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hu.lottery.R;
import com.hu.lottery.ui.base.BaseFragment;
import com.hu.lottery.utils.LogUtis;

/**
 * Created by huqiucheng on 2017/10/31 0031.
 */

public class SelectBallFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LogUtis.logi("SelectBallFragment ---onCreateView()");
        return inflater.inflate(R.layout.layout_fragment_select_ball, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtis.logi("SelectBallFragment ---onDestroyView()");
    }
}
