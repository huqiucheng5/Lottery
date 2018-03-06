package com.hu.lottery.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hu.lottery.R;

import java.util.Arrays;

import static com.hu.lottery.ui.ChooseBallActivity.isDataInit;

/**
 * Created by huqiucheng on 2017/11/1 0001.
 */

public class SelectBlueBallAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private String[] lostBlueData;
    private boolean[] blueFlags;

    public SelectBlueBallAdapter(Context mContext, String[] lostBlueData) {
        inflater = LayoutInflater.from(mContext);
        this.lostBlueData = lostBlueData;
        blueFlags = new boolean[lostBlueData.length];
    }

    public void setBlueBallFlags(int[] flag) {
        if (null != flag && flag.length > 0) {
            Arrays.fill(blueFlags, false);
            for (int i = 0; i < flag.length; i++) {
                blueFlags[flag[i] - 1] = true;
            }
            notifyDataSetChanged();
        }
    }

    public void setBlueBallFlagByPosition(int position) {
        blueFlags[position] = !blueFlags[position];
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return lostBlueData.length;
    }

    @Override
    public Object getItem(int i) {
        return lostBlueData[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == view) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.layout_gridview_blueball_item, null);
            holder.tvBall = view.findViewById(R.id.tv_ball);
            holder.tvLost = view.findViewById(R.id.tv_lost);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvBall.setText(formatString(i + 1));
        if (blueFlags[i]) {
            holder.tvBall.setBackgroundResource(R.mipmap.ball_selected_blue);
            holder.tvBall.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.tvBall.setBackgroundResource(R.mipmap.ball_default);
            holder.tvBall.setTextColor(Color.parseColor("#0000FF"));
        }
        if (isDataInit) {
            holder.tvLost.setText(lostBlueData[i]);
        } else {
            holder.tvLost.setText("-");
        }
        return view;
    }

    public static class ViewHolder {
        TextView tvBall;
        TextView tvLost;

    }

    public String formatString(int str) {
        return String.format("%02d", str);
    }
}
