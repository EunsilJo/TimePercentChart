package com.github.eunsiljo.timepercentchart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EunsilJo on 2017. 9. 27..
 */

public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> items = new ArrayList<String>();

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void add(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<String> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    private DemoItemViewHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(DemoItemViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_demo_item, parent, false);
        return new DemoItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DemoItemViewHolder h = (DemoItemViewHolder)holder;
        h.setDemoItem(items.get(position));
        h.setOnItemClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}