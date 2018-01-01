package com.github.eunsiljo.timepercentchartlib.data;

import android.support.annotation.ColorRes;

import java.util.ArrayList;

/**
 * Created by EunsilJo on 2017. 11. 9..
 */

public class TimeChartData extends ChartData{
    private ArrayList<TimeData> values;

    public TimeChartData(@ColorRes int colorRes, LegendData legend, ArrayList<TimeData> values) {
        super(colorRes, legend);
        this.values = values;
    }

    public ArrayList<TimeData> getValues() {
        return values;
    }
}
