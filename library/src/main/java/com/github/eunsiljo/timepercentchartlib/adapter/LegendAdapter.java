package com.github.eunsiljo.timepercentchartlib.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.github.eunsiljo.timepercentchartlib.R;
import com.github.eunsiljo.timepercentchartlib.data.ChartData;
import com.github.eunsiljo.timepercentchartlib.viewholder.LegendViewHolder;

/**
 * Created by EunsilJo on 2017. 11. 9..
 */

public class LegendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChartData> items = new ArrayList<ChartData>();

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void add(ChartData item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<ChartData> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public List<ChartData> getItems() {
        return items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_chart_legend_item, parent, false);
        return new LegendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LegendViewHolder)holder).setLegendItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}