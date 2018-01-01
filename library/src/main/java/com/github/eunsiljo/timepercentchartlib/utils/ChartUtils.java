package com.github.eunsiljo.timepercentchartlib.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.github.eunsiljo.timepercentchartlib.R;
import com.github.eunsiljo.timepercentchartlib.data.TimeData;

/**
 * Created by EunsilJo on 2017. 11. 13..
 */

public class ChartUtils {

    public static ArrayList<TimeData> getValidateTimeData(long startMillis, long endMillis,
                                                          ArrayList<TimeData> values){
        ArrayList<TimeData> result = new ArrayList<>();
        if(values != null && values.size() != 0){
            long prevEnd = startMillis;

            for(TimeData value : values){
                long start = value.getStartMills();
                long end = value.getStopMills();

                if(start < endMillis && end > prevEnd && start < end){
                    if(start < startMillis){
                        start = startMillis;
                    }
                    if(end > endMillis){
                        end = endMillis;
                    }
                    result.add(new TimeData(start, end));

                    prevEnd = value.getStopMills();
                }
            }
        }
        return result;
    }

    public static long getTotalTime(ArrayList<TimeData> values){
        if(values == null){
            return 0;
        }
        long result = 0;

        for(TimeData value : values){
            result += value.getStopMills() - value.getStartMills();
        }

        return result;
    }

    // =============================================================================
    // Convert
    // =============================================================================

    /**
     * Convert dp to px
     *
     * @param context context
     * @param px      pixel
     * @return dp
     */
    public static int convertPxToDp(Context context, float px) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return (int) dp;
    }

    // =============================================================================
    // format
    // =============================================================================

    public static DecimalFormat getFirstDecimalFormat(){
        return new DecimalFormat("#.#");
    }

    public static DecimalFormat getSecondDecimalFormat(){
        return new DecimalFormat("#.##");
    }

    public static String formatTime(Context context, int time) {
        int h = time / 3600;
        int m = (time - h * 3600) / 60;
        String strTime;
        strTime = String.format(context.getString(R.string.percent_chart_legend_format_time_h_m), h, m);
        return strTime;
    }

    public static String formatTimeLocale(Context context, int time) {
        int h = time / 3600;
        int m = (time - h * 3600) / 60;
        String strTime;
        if (h == 0) {
            strTime = String.format(context.getString(R.string.time_chart_legend_format_time_m), m);
        } else if (m == 0){
            strTime = String.format(context.getString(R.string.time_chart_legend_format_time_h), h);
        } else {
            strTime = String.format(context.getString(R.string.time_chart_legend_format_time_h_m), h, m);
        }
        return strTime;
    }
}
