package com.github.eunsiljo.timepercentchartlib.data;

/**
 * Created by EunsilJo on 2017. 11. 9..
 */

public class LegendData {
    private String legend;
    private String value;
    private String subValue;
    private boolean showValue = true;
    private boolean showSubValue = true;

    public LegendData(String legend) {
        this.legend = legend;
    }

    public String getLegend() {
        return legend;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSubValue() {
        return subValue;
    }

    public void setSubValue(String subValue) {
        this.subValue = subValue;
    }

    public boolean isShowValue() {
        return showValue;
    }

    public void setShowValue(boolean showValue) {
        this.showValue = showValue;
    }

    public boolean isShowSubValue() {
        return showSubValue;
    }

    public void setShowSubValue(boolean showSubValue) {
        this.showSubValue = showSubValue;
    }
}
