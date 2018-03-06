package com.hu.lottery.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hu.lottery.Constances.LotteryConstance;
import com.hu.lottery.R;
import com.hu.lottery.ui.base.BaseFragment;
import com.hu.lottery.utils.LogUtis;

/**
 * 彩票开奖信息
 * Created by huqiucheng on 2017/10/31 0031.
 */

public class LotteryInformationFragment extends BaseFragment implements View.OnClickListener {

    private ImageView ivSsq, ivDlt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_lottery_information, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivSsq = view.findViewById(R.id.iv_ssq);
        ivDlt = view.findViewById(R.id.iv_dlt);
        ivSsq.setOnClickListener(this);
        ivDlt.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_ssq:
                Intent ssqIntent = new Intent(getActivity(), OpenLotteryActivity.class);
                ssqIntent.putExtra(LotteryConstance.KEY_TYPE, LotteryConstance.TYPE_SHUANGSEQIU);
                getActivity().startActivity(ssqIntent);
                break;
            case R.id.iv_dlt:
                Intent dltIntent = new Intent(getActivity(), OpenLotteryActivity.class);
                dltIntent.putExtra(LotteryConstance.KEY_TYPE, LotteryConstance.TYPE_DALETOU);
                getActivity().startActivity(dltIntent);
                break;
        }

    }
}
