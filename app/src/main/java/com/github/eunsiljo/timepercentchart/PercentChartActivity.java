package com.github.eunsiljo.timepercentchart;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.eunsiljo.timepercentchartlib.data.LegendData;
import com.github.eunsiljo.timepercentchartlib.data.PercentChartData;
import com.github.eunsiljo.timepercentchartlib.data.TimeChartData;
import com.github.eunsiljo.timepercentchartlib.view.PercentChartView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PercentChartActivity extends AppCompatActivity {

    private PercentChartView percentChart;
    private View btnRefresh;

    private ArrayList<PercentChartData> mSamples = new ArrayList<>();

    private List<String> mHeaders = Arrays.asList("Korean", "English", "Math", "Science", "Physics");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent_chart);
        initLayout();
        initListener();
        initData();
    }

    private void initLayout(){
        getSupportActionBar().setTitle("PercentChart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        percentChart = (PercentChartView)findViewById(R.id.percentChart);
        btnRefresh = findViewById(R.id.btnRefresh);
    }

    private void initListener(){
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                percentChart.setPercentChart(getSamples(mHeaders));
            }
        });
    }

    private void initData(){
        //initSamples();

        percentChart.setDrawValue(PercentChartView.DrawValue.PERCENT);
        percentChart.setDrawValueColor(R.color.white);
        percentChart.setUnit(PercentChartView.TIME_UNIT);

        //percentChart.setPercentChart(mSamples);
        percentChart.setPercentChart(getSamples(mHeaders));
    }

    private ArrayList<PercentChartData> getSamples(List<String> headers){
        TypedArray colors_chart = getResources().obtainTypedArray(R.array.colors_chart);

        ArrayList<PercentChartData> percents = new ArrayList<>();
        for(int i=0; i<headers.size(); i++){
            percents.add(new PercentChartData(colors_chart.getResourceId(i, 0), new LegendData(headers.get(i)),
                    (int)((Math.random() * 10) + 1) * 30 * 60 * 1000));
        }
        return percents;
    }

    private void initSamples(){
        ArrayList<PercentChartData> percents = new ArrayList<>();
        percents.add(new PercentChartData(R.color.color_chart_1, new LegendData("Korean"), 28800000));
        percents.add(new PercentChartData(R.color.color_chart_2, new LegendData("English"), 28800000));
        percents.add(new PercentChartData(R.color.color_chart_3, new LegendData("Math"), 14400000));
        percents.add(new PercentChartData(R.color.color_chart_4, new LegendData("Science"), 14400000));
        percents.add(new PercentChartData(R.color.color_chart_5, new LegendData("Physics"), 7200000));

        mSamples.addAll(percents);
    }

    private void setChartDrawValue(int id){
        switch (id){
            case R.id.action_legend:
                percentChart.setDrawValue(PercentChartView.DrawValue.LEGEND);
                break;
            case R.id.action_percent:
                percentChart.setDrawValue(PercentChartView.DrawValue.PERCENT);
                break;
            case R.id.action_value:
                percentChart.setDrawValue(PercentChartView.DrawValue.VALUE);
                break;
            case R.id.action_none:
                percentChart.setDrawValue(PercentChartView.DrawValue.NONE);
                break;
        }
        btnRefresh.callOnClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_percent_chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }else{
            setChartDrawValue(id);
        }
        return super.onOptionsItemSelected(item);
    }
}
