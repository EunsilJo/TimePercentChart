package com.github.eunsiljo.timepercentchartlib.data;

import android.support.annotation.ColorRes;

/**
 * Created by EunsilJo on 2017. 11. 9..
 */

public class ChartData {
    private int colorRes;
    private LegendData legend;

    public ChartData(@ColorRes int colorRes, LegendData legend) {
        this.colorRes = colorRes;
        this.legend = legend;
    }

    public int getColorRes() {
        return colorRes;
    }

    public LegendData getLegend() {
        return legend;
    }
}
