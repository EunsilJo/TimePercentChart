package com.github.eunsiljo.timepercentchartlib.view;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.github.eunsiljo.timepercentchartlib.R;
import com.github.eunsiljo.timepercentchartlib.adapter.LegendAdapter;
import com.github.eunsiljo.timepercentchartlib.data.ChartData;
import com.github.eunsiljo.timepercentchartlib.data.LegendData;
import com.github.eunsiljo.timepercentchartlib.data.PercentChartData;
import com.github.eunsiljo.timepercentchartlib.utils.ChartUtils;

/**
 * Created by EunsilJo on 2017. 11. 13..
 */

public class PercentChartView extends LinearLayout {

    public enum DrawValue {
        LEGEND,
        PERCENT,
        VALUE,
        NONE
    }
    private DrawValue mDrawValue = DrawValue.NONE;
    private int mDrawValueColor = R.color.black;

    private LinearLayout layoutPercent;

    private RecyclerView recyclerLegend;
    private LegendAdapter mLegendAdapter;
    private LinearLayoutManager mLegendLayoutManager;

    public static final String PERCENT_UNIT = "%";
    public static final String TIME_UNIT = "T";
    private String mUnit = "";
    private boolean showPercentUnit = true;
    private int mMax = 100;
    private int mLabelCount = 5;
    private View layoutXUnit;
    private LinearLayout layoutX;
    private TextView txtUnit;


    public PercentChartView(Context context) {
        super(context);
        init();
    }

    public PercentChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PercentChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_percent_chart, this);

        layoutPercent = (LinearLayout)findViewById(R.id.layoutPercent);

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

        layoutXUnit = findViewById(R.id.layoutXUnit);
        layoutX = (LinearLayout)findViewById(R.id.layoutX);
        txtUnit = (TextView)findViewById(R.id.txtUnit);

        setX();
    }

    public void setPercentChart(ArrayList<PercentChartData> percents){
        layoutPercent.removeAllViewsInLayout();
        mLegendAdapter.clear();

        if(percents != null && percents.size() != 0) {
            ArrayList<ChartData> legends = new ArrayList<>();
            float total = 0;
            for (PercentChartData percent : percents) {
                total += percent.getValue();
            }
            for (PercentChartData percent : percents) {
                LegendData legend = percent.getLegend();

                float v_percent = percent.getValue() / total * 100;

                legend.setValue(ChartUtils.getFirstDecimalFormat().format(v_percent) + PERCENT_UNIT);

                String v_value = ChartUtils.getFirstDecimalFormat().format(percent.getValue()) + mUnit;
                if(mUnit.equals(TIME_UNIT)){
                    v_value = ChartUtils.formatTime(getContext(), (int)percent.getValue() / 1000);
                }
                legend.setSubValue(v_value);

                legend.setShowValue(mDrawValue != DrawValue.PERCENT);

                legends.add(new ChartData(percent.getColorRes(), legend));

                TextView t = new TextView(getContext());
                t.setText(getDrawValue(legend, mDrawValue));
                t.setTextSize(ChartUtils.convertPxToDp(getContext(), getResources().getDimensionPixelSize(R.dimen.text_micro)));
                t.setTextColor(getResources().getColor(mDrawValueColor));
                t.setGravity(Gravity.CENTER);
                t.setBackgroundResource(percent.getColorRes());
                t.setEllipsize(TextUtils.TruncateAt.END);

                LayoutParams lp = new LayoutParams(0,
                        ViewGroup.LayoutParams.MATCH_PARENT, v_percent);
                t.setLayoutParams(lp);

                layoutPercent.addView(t);
            }
            mLegendAdapter.addAll(legends);
            mMax = (int)total;
        }
        setX();
    }

    public List<ChartData> getLegends(){
        return mLegendAdapter.getItems();
    }

    private String getDrawValue(LegendData legend, DrawValue drawValue){
        switch (drawValue){
            case LEGEND:
                return legend.getLegend();
            case PERCENT:
                return legend.getValue();
            case VALUE:
                return legend.getSubValue();
        }
        return "";
    }

    public void setDrawValue(DrawValue drawValue){
        mDrawValue = drawValue;
    }

    public void setDrawValueColor(@ColorRes int colorRes){
        mDrawValueColor = colorRes;
    }

    public void setUnit(String unit){
        mUnit = unit;
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

    private void setX(){
        layoutX.removeAllViewsInLayout();
        String unit = PERCENT_UNIT;
        int max = 100;
        if(!showPercentUnit){
            unit = mUnit;
            max = mMax;
        }
        txtUnit.setText("(" + unit + ")");

        ArrayList<String> Xs = new ArrayList<>();
        int interval = max / (mLabelCount - 1);
        for(int i=0; i<mLabelCount; i++){
            int x = i * interval;
            if(i == mLabelCount-1){
                x = max;
            }
            Xs.add(String.valueOf(x));
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

    public void showX(boolean show){
        if(show){
            layoutXUnit.setVisibility(VISIBLE);
        }else{
            layoutXUnit.setVisibility(GONE);
        }
    }

    public void showLegend(boolean show){
        if(show){
            recyclerLegend.setVisibility(VISIBLE);
        }else{
            recyclerLegend.setVisibility(GONE);
        }
    }

    public void showPercentUnit(boolean show) {
        this.showPercentUnit = show;
    }
}
