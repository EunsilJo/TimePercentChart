package com.github.eunsiljo.timepercentchart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mListView;
    private DemoAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        initListener();
        initData();
    }

    private void initLayout(){
        mAdapter = new DemoAdapter();
        mListView = (RecyclerView)findViewById(R.id.recycler);
        mListView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mListView.setLayoutManager(mLayoutManager);
    }

    private void initListener(){
        mAdapter.setOnItemClickListener(new DemoItemViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(position);
            }
        });
    }

    private static final int PERCENT_CHART = 0;
    private static final int TIME_CHART = 1;

    private void initData(){
        ArrayList<String> demos = new ArrayList<>();
        demos.add("PercentChart");
        demos.add("TimeChart");
        mAdapter.addAll(demos);
    }

    private void startActivity(int position){
        switch (position){
            case PERCENT_CHART:
                startActivity(new Intent(MainActivity.this, PercentChartActivity.class));
                break;
            case TIME_CHART:
                startActivity(new Intent(MainActivity.this, TimeChartActivity.class));
                break;
        }
    }
}
