package com.github.eunsiljo.timepercentchartlib.viewholder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.eunsiljo.timepercentchartlib.R;
import com.github.eunsiljo.timepercentchartlib.data.ChartData;
import com.github.eunsiljo.timepercentchartlib.data.LegendData;


/**
 * Created by EunsilJo on 2017. 11. 9..
 */

public class LegendViewHolder extends RecyclerView.ViewHolder{

    private Context mContext;
    private View imgLegend;
    private TextView txtLegend;
    private TextView txtValue;
    private TextView txtSubValue;

    public LegendViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();

        imgLegend = itemView.findViewById(R.id.imgLegend);
        txtLegend = (TextView)itemView.findViewById(R.id.txtLegend);
        txtValue = (TextView)itemView.findViewById(R.id.txtValue);
        txtSubValue = (TextView)itemView.findViewById(R.id.txtSubValue);
    }

    public void setLegendItem(ChartData chartData) {
        if(chartData != null) {
            int color = chartData.getColorRes();
            LegendData l = chartData.getLegend();
            imgLegend.setBackgroundResource(color);
            txtValue.setTextColor(ContextCompat.getColor(mContext, color));
            txtSubValue.setTextColor(ContextCompat.getColor(mContext, color));

            String legend = l.getLegend();
            String value = l.getValue();
            String subValue = l.getSubValue();
            if(legend != null){
                txtLegend.setText(legend);
            }
            if(value != null && l.isShowValue()) {
                txtValue.setText(value);
                txtValue.setVisibility(View.VISIBLE);
            }else{
                txtValue.setVisibility(View.GONE);
            }
            if(subValue != null && l.isShowSubValue()) {
                txtSubValue.setText("(" + subValue + ")");
                txtSubValue.setVisibility(View.VISIBLE);
            }else{
                txtSubValue.setVisibility(View.GONE);
            }
        }
    }
}