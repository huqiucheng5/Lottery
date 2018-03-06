package com.hu.lottery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.hu.lottery.Constances.LotteryConstance;
import com.hu.lottery.R;
import com.hu.lottery.adapter.HistoryLotteryAdapter;
import com.hu.lottery.bean.HistoryLottery;
import com.hu.lottery.network.NetTools;
import com.hu.lottery.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class OpenLotteryActivity extends BaseActivity implements NetTools.RequestNetDataResultListener {

    private int type = 0;

    private ListView listviewHistory;
    private HistoryLotteryAdapter historyLotteryAdapter;
    private List<HistoryLottery> historyLotteryList = new ArrayList<>();
    int page = 1;
    private boolean isRequestData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_lottery);
        final Intent intent = getIntent();
        type = intent.getIntExtra(LotteryConstance.KEY_TYPE, LotteryConstance.TYPE_SHUANGSEQIU);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (type == LotteryConstance.TYPE_SHUANGSEQIU) {
            toolbar.setTitle("SHUANGSEQIU");
        } else if (type == LotteryConstance.TYPE_DALETOU) {
            toolbar.setTitle("DALETOU");
        }
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRequestData) {
                    Toast.makeText(OpenLotteryActivity.this, "is requesting...", Toast.LENGTH_SHORT).show();
                } else {
                    page++;
                    initData();
                }
            }
        });
        NetTools.getInstance(this).setNetDataResultListener(this);
        listviewHistory = findViewById(R.id.listview_history);
        historyLotteryAdapter = new HistoryLotteryAdapter(this, historyLotteryList);
        listviewHistory.setAdapter(historyLotteryAdapter);
        initData();
    }

    private void initData() {
        isRequestData = true;
        NetTools.getInstance(this).getHistoryData(type == LotteryConstance.TYPE_SHUANGSEQIU ? LotteryConstance.SHUANGESEQIU_GID : LotteryConstance.DALETOU_GID, page);
    }

    @Override
    protected void onDestroy() {
        NetTools.getInstance(this).setNetDataResultListener(null);
        super.onDestroy();
    }

    @Override
    public void onSuccess(Object obj) {
        isRequestData = false;
        if (obj instanceof List) {
            List<HistoryLottery> list = ((List<HistoryLottery>) obj);
            if (null != list) {
                if (list.size() <= 0) {
                    logi("list size is 0");
                    Toast.makeText(OpenLotteryActivity.this, "no more data!", Toast.LENGTH_SHORT).show();
                } else {
                    logi("list size = " + list.size());
                    historyLotteryList.addAll(list);
                    historyLotteryAdapter.notifyDataSetChanged();
                }
            } else {
                logi("list is null");
            }
        }

    }

    @Override
    public void onError(int errorType) {
        loge("onError() errorType = " + errorType);
        isRequestData = false;
    }
}
