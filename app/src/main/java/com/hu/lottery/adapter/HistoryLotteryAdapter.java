package com.hu.lottery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hu.lottery.R;
import com.hu.lottery.bean.HistoryLottery;

import java.util.List;

/**
 * Created by huqiucheng on 2017/11/2 0002.
 */

public class HistoryLotteryAdapter extends BaseAdapter {

    private List<HistoryLottery> list;
    private LayoutInflater inflater;

    public HistoryLotteryAdapter(Context mContext, List<HistoryLottery> list) {
        this.list = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
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
            view = inflater.inflate(R.layout.layout_listview_history_item, null);
            holder.tvPid = view.findViewById(R.id.tv_pdi);
            holder.tvTime = view.findViewById(R.id.tv_time);
            holder.tvNumber = view.findViewById(R.id.tv_number);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        HistoryLottery historyLottery = list.get(i);
        holder.tvPid.setText(historyLottery.getPdi());
        holder.tvTime.setText(historyLottery.getAtime());
        holder.tvNumber.setText(historyLottery.getAcode());
        return view;
    }

    static class ViewHolder {
        TextView tvPid, tvTime, tvNumber;
    }
}
