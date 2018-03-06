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

public class SelectRedBallAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private String[] lostRedData;
    private boolean[] redFlags;


    public SelectRedBallAdapter(Context mContext, String[] lostRedData) {
        inflater = LayoutInflater.from(mContext);
        this.lostRedData = lostRedData;
        redFlags = new boolean[lostRedData.length];
    }


    public void setRedBallFlags(int[] flag) {
        if (null != flag && flag.length > 0) {
            Arrays.fill(redFlags, false);
            for (int i = 0; i < flag.length; i++) {
                redFlags[flag[i] - 1] = true;
            }
            notifyDataSetChanged();
        }
    }

    public void setRedBallFlagByPosition(int position) {
        redFlags[position] = !redFlags[position];
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return lostRedData.length;
    }

    @Override
    public Object getItem(int i) {
        return lostRedData[i];
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
            view = inflater.inflate(R.layout.layout_gridview_redball_item, null);
            holder.tvBall = view.findViewById(R.id.tv_ball);
            holder.tvLost = view.findViewById(R.id.tv_lost);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvBall.setText(formatString(i + 1));
        if (redFlags[i]) {
            holder.tvBall.setBackgroundResource(R.mipmap.ball_selected_red);
            holder.tvBall.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.tvBall.setBackgroundResource(R.mipmap.ball_default);
            holder.tvBall.setTextColor(Color.parseColor("#FF0000"));
        }
        if (isDataInit) {
            holder.tvLost.setText(lostRedData[i]);
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
