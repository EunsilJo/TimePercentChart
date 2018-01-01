package com.github.eunsiljo.timepercentchartlib.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import com.github.eunsiljo.timepercentchartlib.R;
import com.github.eunsiljo.timepercentchartlib.adapter.LegendAdapter;
import com.github.eunsiljo.timepercentchartlib.adapter.TimeChartAdapter;
import com.github.eunsiljo.timepercentchartlib.data.ChartData;
import com.github.eunsiljo.timepercentchartlib.data.LegendData;
import com.github.eunsiljo.timepercentchartlib.data.TimeChartData;
import com.github.eunsiljo.timepercentchartlib.utils.ChartUtils;


/**
 * Created by EunsilJo on 2017. 11. 9..
 */

public class TimeChartView extends LinearLayout {

    private RecyclerView recyclerChart;
    private TimeChartAdapter mChartAdapter;
    private LinearLayoutManager mChartLayoutManager;

    private RecyclerView recyclerLegend;
    private LegendAdapter mLegendAdapter;
    private LinearLayoutManager mLegendLayoutManager;

    private int mStartHour = 0;
    private int mLabelCount = 5;
    private LinearLayout layoutX;

    public TimeChartView(Context context) {
        super(context);
        init();
    }

    public TimeChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimeChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_time_chart, this);

        recyclerChart = (RecyclerView)findViewById(R.id.recyclerChart);
        mChartAdapter = new TimeChartAdapter();
        mChartLayoutManager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerChart.setAdapter(mChartAdapter);
        recyclerChart.setLayoutManager(mChartLayoutManager);

        recyclerLegend = (RecyclerView)findViewById(R.id.recyclerLegend);
        mLegendAdapter = new LegendAdapter();
        mLegendLayoutManager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerLegend.setAdapter(mLegendAdapter);
        recyclerLegend.setLayoutManager(mLegendLayoutManager);

        layoutX = (LinearLayout)findViewById(R.id.layoutX);

        setX(mStartHour);
    }

    public void setTimeChart(long date, ArrayList<TimeChartData> charts){
        mChartAdapter.clear();
        mLegendAdapter.clear();
        if(charts != null && charts.size() != 0) {
            DateTime start = new DateTime(date).withHourOfDay(mStartHour);
            long startMillis = start.getMillis();
            long endMillis = start.plusHours(24).getMillis();

            mChartAdapter.setRange(startMillis, endMillis);
            mChartAdapter.addAll(charts);

            ArrayList<ChartData> legends = new ArrayList<>();
            for(TimeChartData chart : charts){
                LegendData legend = chart.getLegend();
                long value = ChartUtils.getTotalTime(
                        ChartUtils.getValidateTimeData(startMillis, endMillis, chart.getValues())) / 1000;
                legend.setValue(ChartUtils.formatTimeLocale(getContext(), (int)value));
                legends.add(new ChartData(chart.getColorRes(), legend));
            }
            mLegendAdapter.addAll(legends);
        }
        setX(mStartHour);
    }

    public List<ChartData> getLegends(){
        return mLegendAdapter.getItems();
    }

    public void setStartHour(int start){
        if(start < 0 || start > 24){
            start = 0;
        }
        mStartHour = start;
    }

    public void setLabelCount(int count){
        if(count < 2){
            count = 2;
        }
        if(count > 9){
            count = 9;
        }
        mLabelCount = count;
    }

    private void setX(int startHour){
        layoutX.removeAllViewsInLayout();

        ArrayList<String> Xs = new ArrayList<>();
        int hour = startHour;
        int interval = 24 / (mLabelCount - 1);
        for(int i=0; i<mLabelCount; i++){
            Xs.add(String.format(getContext().getString(R.string.time_chart_x_format), hour));
            hour += interval;
            if(hour > 24){
                hour -= 24;
            }
        }

        for(int i=0; i<Xs.size(); i++){
            TextView t = new TextView(getContext());
            t.setText(Xs.get(i));
            t.setTextSize(ChartUtils.convertPxToDp(getContext(), getResources().getDimensionPixelSize(R.dimen.text_micro)));
            t.setTextColor(getResources().getColor(R.color.black));
            t.setGravity(Gravity.CENTER_HORIZONTAL);

            LayoutParams lp = new LayoutParams(
                    getResources().getDimensionPixelSize(R.dimen.common_chart_x_width),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            t.setLayoutParams(lp);

            layoutX.addView(t);

            if(i != (Xs.size() - 1)){
                View v = new View(getContext());
                v.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        1, 1));
                layoutX.addView(v);
            }
        }
    }

    public void showLegend(boolean show){
        if(show){
            recyclerLegend.setVisibility(VISIBLE);
        }else{
            recyclerLegend.setVisibility(GONE);
        }
    }
}
