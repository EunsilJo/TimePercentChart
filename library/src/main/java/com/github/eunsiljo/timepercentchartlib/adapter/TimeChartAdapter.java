package com.github.eunsiljo.timepercentchartlib.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.github.eunsiljo.timepercentchartlib.R;
import com.github.eunsiljo.timepercentchartlib.data.TimeChartData;
import com.github.eunsiljo.timepercentchartlib.viewholder.TimeChartItemViewHolder;


/**
 * Created by EunsilJo on 2017. 11. 9..
 */

public class TimeChartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long startMillis;
    private long endMillis;
    private List<TimeChartData> items = new ArrayList<TimeChartData>();

    public void setRange(long startMillis, long endMillis) {
        this.startMillis = startMillis;
        this.endMillis = endMillis;
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void add(TimeChartData item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<TimeChartData> items) {
        int positionStart = this.items.size();
        this.items.addAll(items);
        notifyItemRangeInserted(positionStart, items.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_time_chart_item, parent, false);
        return new TimeChartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TimeChartItemViewHolder)holder).setChartItem(startMillis, endMillis, items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}