package com.github.eunsiljo.timepercentchartlib.data;


import android.support.annotation.ColorRes;

/**
 * Created by EunsilJo on 2017. 11. 13..
 */

public class PercentChartData extends ChartData{
    private float value;

    public PercentChartData(@ColorRes int colorRes, LegendData legend, float value) {
        super(colorRes, legend);
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}