package com.github.eunsiljo.timepercentchart;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


/**
 * Created by EunsilJo on 2017. 9. 27..
 */

public class DemoItemViewHolder extends RecyclerView.ViewHolder{

    private String demo;

    private TextView txtName;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    private OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public DemoItemViewHolder(View itemView) {
        super(itemView);

        txtName = (TextView)itemView.findViewById(R.id.txtName);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, getLayoutPosition());
                }
            }
        });
    }

    public void setDemoItem(String demo) {
        if(demo != null) {
            this.demo = demo;
            txtName.setText(demo);
        }
    }
}

