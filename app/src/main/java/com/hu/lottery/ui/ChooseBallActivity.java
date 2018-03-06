package com.hu.lottery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.hu.lottery.Constances.LotteryConstance;
import com.hu.lottery.R;
import com.hu.lottery.adapter.SelectBlueBallAdapter;
import com.hu.lottery.adapter.SelectRedBallAdapter;
import com.hu.lottery.bean.LostLottery;
import com.hu.lottery.network.NetTools;
import com.hu.lottery.ui.base.BaseActivity;
import com.hu.lottery.utils.LogUtis;
import com.hu.lottery.utils.LotteryDataUtils;

import java.util.Arrays;

public class ChooseBallActivity extends BaseActivity implements NetTools.RequestNetDataResultListener {

    public static boolean isDataInit = false;

    int type;
    Toolbar toolbar;
    FloatingActionButton fab;
    private TextView tvText;
    private GridView gridviewRedball, gridviewBlueBall;
    private String[] lostRedData, lostBlueData;
    private SelectRedBallAdapter selectRedBallAdapter;
    private SelectBlueBallAdapter selectBlueBallAdapter;

    private static final int MSG_LOAD_REA_DATA = 1;
    private static final int MSG_LOAD_BLUE_DATA = 2;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_LOAD_REA_DATA:
                    break;
                case MSG_LOAD_BLUE_DATA:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        type = intent.getIntExtra(LotteryConstance.KEY_TYPE, LotteryConstance.TYPE_SHUANGSEQIU);
        setContentView(R.layout.activity_choose_ball);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (type == LotteryConstance.TYPE_SHUANGSEQIU) {
            toolbar.setTitle("SHUANGSEQIU");
        } else if (type == LotteryConstance.TYPE_DALETOU) {
            toolbar.setTitle("DALETOU");
        }
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRandomData();
//                initData();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        tvText = findViewById(R.id.tv_text);
        gridviewRedball = findViewById(R.id.gridview_redball);
        gridviewBlueBall = findViewById(R.id.gridview_blueball);
        if (type == LotteryConstance.TYPE_SHUANGSEQIU) {
            lostRedData = new String[33];
            lostBlueData = new String[16];
        } else if (type == LotteryConstance.TYPE_DALETOU) {
            lostRedData = new String[35];
            lostBlueData = new String[12];
        }
        selectRedBallAdapter = new SelectRedBallAdapter(this, lostRedData);
        gridviewRedball.setAdapter(selectRedBallAdapter);
        selectBlueBallAdapter = new SelectBlueBallAdapter(this, lostBlueData);
        gridviewBlueBall.setAdapter(selectBlueBallAdapter);
        gridviewRedball.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectRedBallAdapter.setRedBallFlagByPosition(i);
            }
        });
        gridviewBlueBall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectBlueBallAdapter.setBlueBallFlagByPosition(i);
            }
        });
        initData();
        getRandomData();

    }

    private void getRandomData() {
        int[] randomDatas = null;
        if (type == LotteryConstance.TYPE_SHUANGSEQIU) {
            randomDatas = LotteryDataUtils.getInstance().getSsqData();
            selectRedBallAdapter.setRedBallFlags(Arrays.copyOfRange(randomDatas, 0, randomDatas.length - 1));
            selectBlueBallAdapter.setBlueBallFlags(Arrays.copyOfRange(randomDatas, randomDatas.length - 1, randomDatas.length));
        } else if (type == LotteryConstance.TYPE_DALETOU) {
            randomDatas = LotteryDataUtils.getInstance().getDltData();
            selectRedBallAdapter.setRedBallFlags(Arrays.copyOfRange(randomDatas, 0, randomDatas.length - 2));
            selectBlueBallAdapter.setBlueBallFlags(Arrays.copyOfRange(randomDatas, randomDatas.length - 2, randomDatas.length));
        }
        tvText.setText(LotteryDataUtils.getInstance().getLotterysString(type, randomDatas));
    }

    @Override
    protected void onResume() {
        super.onResume();
        NetTools.getInstance(this).setNetDataResultListener(this);
    }

    private void initData() {
        NetTools.getInstance(this).getLostData(type == LotteryConstance.TYPE_SHUANGSEQIU ? LotteryConstance.SHUANGESEQIU_GID : LotteryConstance.DALETOU_GID);
    }

    private String getLotteryData() {
        return LotteryDataUtils.getInstance().getLotterysString(type);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NetTools.getInstance(this).setNetDataResultListener(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDataInit = false;
    }

    @Override
    public void onSuccess(Object obj) {
        LogUtis.logi("onSuccess()");
        if (obj instanceof LostLottery) {
            isDataInit = true;
            LostLottery lostLottery = (LostLottery) obj;
            String redLost = lostLottery.getBallList().get(0).getCuryl();
            String[] redLostArray = redLost.split(",");
            System.arraycopy(redLostArray, 0, lostRedData, 0, lostRedData.length);
            selectRedBallAdapter.notifyDataSetChanged();
            String blueLost = lostLottery.getBallList().get(1).getCuryl();
            String[] blueLostArray = blueLost.split(",");
            System.arraycopy(blueLostArray, 0, lostBlueData, 0, lostBlueData.length);
            selectBlueBallAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onError(int errorType) {

    }
}
