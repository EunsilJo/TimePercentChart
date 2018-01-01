package com.github.eunsiljo.timepercentchartlib.data;

/**
 * Created by EunsilJo on 2017. 11. 9..
 */

public class TimeData {
    private long startMills;
    private long stopMills;

    public TimeData(long startMills, long stopMills) {
        this.startMills = startMills;
        this.stopMills = stopMills;
    }

    public long getStartMills() {
        return startMills;
    }

    public long getStopMills() {
        return stopMills;
    }
}
