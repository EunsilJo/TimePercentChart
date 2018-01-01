package com.github.eunsiljo.timepercentchart;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.github.eunsiljo.timepercentchartlib.data.LegendData;
import com.github.eunsiljo.timepercentchartlib.data.TimeChartData;
import com.github.eunsiljo.timepercentchartlib.data.TimeData;
import com.github.eunsiljo.timepercentchartlib.view.TimeChartView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeChartActivity extends AppCompatActivity {

    private TimeChartView timeChart;
    private View btnRefresh;

    private ArrayList<TimeChartData> mSamples = new ArrayList<>();

    private List<String> mHeaders =  Arrays.asList("Plan", "Do");
    private long mNow = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_chart);
        initLayout();
        initListener();
        initData();
    }

    private void initLayout(){
        getSupportActionBar().setTitle("TimeChart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        timeChart = (TimeChartView)findViewById(R.id.timeChart);
        btnRefresh = findViewById(R.id.btnRefresh);
    }

    private void initListener(){
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeChart.setTimeChart(mNow, getSamples(mNow, mHeaders));
            }
        });
    }

    private void initData(){
        //initSamples();

        DateTime now = DateTime.now();
        mNow = now.withTimeAtStartOfDay().getMillis();

        timeChart.setStartHour(6);

        //timeChart.setTimeChart(getMillis("2017-11-10 00:00:00"), mSamples);
        timeChart.setTimeChart(mNow, getSamples(mNow, mHeaders));
    }

    private ArrayList<TimeChartData> getSamples(long date, List<String> headers){
        TypedArray colors_chart = getResources().obtainTypedArray(R.array.colors_chart);

        ArrayList<TimeChartData> charts = new ArrayList<>();
        for(int i=0; i<headers.size(); i++){
            ArrayList<TimeData> values = new ArrayList<>();
            DateTime start = new DateTime(date);
            DateTime end = start.plusMinutes((int)((Math.random() * 10) + 1) * 30);
            for(int j=0; j<7; j++){
                values.add(new TimeData(start.getMillis(), end.getMillis()));

                start = end.plusMinutes((int)((Math.random() * 10) + 1) * 10);
                end = start.plusMinutes((int)((Math.random() * 10) + 1) * 30);
            }
            charts.add(new TimeChartData(colors_chart.getResourceId(i, 0), new LegendData(headers.get(i)), values));
        }

        return charts;
    }

    private void initSamples(){
        ArrayList<TimeData> values = new ArrayList<>();
        values.add(new TimeData(getMillis("2017-11-10 06:00:00"), getMillis("2017-11-10 06:00:22")));
        values.add(new TimeData(getMillis("2017-11-10 07:07:00"), getMillis("2017-11-10 07:17:11")));
        values.add(new TimeData(getMillis("2017-11-10 11:40:00"), getMillis("2017-11-10 13:00:00")));
        values.add(new TimeData(getMillis("2017-11-10 18:00:00"), getMillis("2017-11-10 19:00:00")));
        values.add(new TimeData(getMillis("2017-11-10 23:00:00"), getMillis("2017-11-11 03:00:00")));

        ArrayList<TimeData> values2 = new ArrayList<>();
        values2.add(new TimeData(getMillis("2017-11-10 03:00:00"), getMillis("2017-11-10 18:00:00")));
        values2.add(new TimeData(getMillis("2017-11-10 18:00:00"), getMillis("2017-11-10 20:00:00")));
        values2.add(new TimeData(getMillis("2017-11-11 04:00:00"), getMillis("2017-11-11 07:00:00")));

        ArrayList<TimeChartData> charts = new ArrayList<>();
        charts.add(new TimeChartData(R.color.color_chart_1, new LegendData("Plan"), values));
        charts.add(new TimeChartData(R.color.color_chart_2, new LegendData("Do"), values2));

        mSamples.addAll(charts);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    // =============================================================================
    // Date format
    // =============================================================================

    private long getMillis(String day){
        DateTime date = getDateTimePattern().parseDateTime(day);
        return date.getMillis();
    }

    private DateTimeFormatter getDateTimePattern(){
        return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    }
}
