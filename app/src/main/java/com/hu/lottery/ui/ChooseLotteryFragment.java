package com.hu.lottery.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hu.lottery.Constances.LotteryConstance;
import com.hu.lottery.R;
import com.hu.lottery.ui.base.BaseFragment;
import com.hu.lottery.utils.LogUtis;

/**
 * 选择彩票类型
 * Created by huqiucheng on 2017/10/31 0031.
 */

public class ChooseLotteryFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout layoutSsq, layoutDlt;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LogUtis.logi("ChooseLotteryFragment ---onCreateView()");
        return inflater.inflate(R.layout.layout_fragment_choose_lottery, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutSsq = view.findViewById(R.id.layout_ssq);
        layoutDlt = view.findViewById(R.id.layout_dlt);
        layoutSsq.setOnClickListener(this);
        layoutDlt.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtis.logi("ChooseLotteryFragment ---onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_ssq:
                Intent ssqIntent = new Intent(getActivity(), ChooseBallActivity.class);
                ssqIntent.putExtra(LotteryConstance.KEY_TYPE, LotteryConstance.TYPE_SHUANGSEQIU);
                getActivity().startActivity(ssqIntent);
                break;
            case R.id.layout_dlt:
                Intent dltIntent = new Intent(getActivity(), ChooseBallActivity.class);
                dltIntent.putExtra(LotteryConstance.KEY_TYPE, LotteryConstance.TYPE_DALETOU);
                getActivity().startActivity(dltIntent);
                break;
        }

    }
}
