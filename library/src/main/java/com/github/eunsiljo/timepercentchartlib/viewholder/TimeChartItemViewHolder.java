package com.github.eunsiljo.timepercentchartlib.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import com.github.eunsiljo.timepercentchartlib.R;
import com.github.eunsiljo.timepercentchartlib.data.TimeChartData;
import com.github.eunsiljo.timepercentchartlib.data.TimeData;
import com.github.eunsiljo.timepercentchartlib.utils.ChartUtils;


/**
 * Created by EunsilJo on 2017. 11. 9..
 */

public class TimeChartItemViewHolder extends RecyclerView.ViewHolder{
    private static final int TIME_COLUMN_NUM = 144;
    private static final long TIME_INTERVAL_MILLS = 24 * 60 * 60 * 1000 / TIME_COLUMN_NUM;

    private Context mContext;
    private final View itemView;

    private LinearLayout layoutPercent;
    private View lineTop;


    public TimeChartItemViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        this.itemView = itemView;

        layoutPercent = (LinearLayout)itemView.findViewById(R.id.layoutPercent);
        lineTop = itemView.findViewById(R.id.lineTop);
    }

    public void setChartItem(long startMillis, long endMillis, TimeChartData chartData) {
        layoutPercent.removeAllViewsInLayout();
        if(chartData != null) {
            ArrayList<TimeData> values = ChartUtils.getValidateTimeData(startMillis, endMillis,
                    chartData.getValues());
            if(values == null || values.size() == 0){
                return;
            }

            int index = 0;
            long start = values.get(index).getStartMills() / 1000 * 1000;
            long end = values.get(index).getStopMills() / 1000 * 1000;
            long time = startMillis / 1000 * 1000;

            int tCount = 0;
            int fCount = 0;

            for (int i = 0; i < TIME_COLUMN_NUM; i++) {
                time += TIME_INTERVAL_MILLS;

                if (time > start && time <= end) {
                    if(fCount > 0){
                        addView(android.R.color.transparent, fCount);
                        fCount = 0;
                    }
                    tCount++;
                }else{
                    fCount++;
                }
                if (time >= end) {
                    if(tCount > 0){
                        addView(chartData.getColorRes(), tCount);
                        tCount = 0;
                    }

                    if(index < values.size()-1) {
                        index++;
                        start = values.get(index).getStartMills() / 1000 * 1000;
                        end = values.get(index).getStopMills() / 1000 * 1000;
                    }
                }

                if(i == TIME_COLUMN_NUM - 1){
                    if(fCount > 0){
                        addView(android.R.color.transparent, fCount);
                        fCount = 0;
                    }
                    if(tCount > 0){
                        addView(chartData.getColorRes(), tCount);
                        tCount = 0;
                    }
                }
            }
        }

        if(getLayoutPosition() == 0){
            lineTop.setVisibility(View.VISIBLE);
        }else{
            lineTop.setVisibility(View.GONE);
        }
    }

    private void addView(int colorRes, float count){
        View v = new View(mContext);
        v.setBackgroundResource(colorRes);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, count / TIME_COLUMN_NUM);
        v.setLayoutParams(lp);
        layoutPercent.addView(v);
    }
}