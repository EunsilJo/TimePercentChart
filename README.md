TimePercentChart
===================
[![](https://jitpack.io/v/EunsilJo/TimePercentChart.svg)](https://jitpack.io/#EunsilJo/TimePercentChart) [![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)

Android Library that shows simple percent chart and time chart.

## How to import
Add it in your root build.gradle at the end of repositories:
```java
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency
```java
dependencies {
	compile 'com.github.EunsilJo:TimePercentChart:1.0'
}
```

## How to use
### PercentChartView

<img src="https://github.com/EunsilJo/TimePercentChart/blob/master/screenshots/1.png?raw=true" height="400"/> 

```java
percentChart.setDrawValueColor(R.color.white);
```
You can change the text color of chart value. (default black)
```java
percentChart.setUnit(PercentChartView.TIME_UNIT);
```
You can change the unit of chart value.

<img src="https://github.com/EunsilJo/TimePercentChart/blob/master/screenshots/2.png?raw=true" height="400"/> <img src="https://github.com/EunsilJo/TimePercentChart/blob/master/screenshots/3.png?raw=true" height="400"/> <img src="https://github.com/EunsilJo/TimePercentChart/blob/master/screenshots/4.png?raw=true" height="400"/> 

```java
percentChart.setDrawValue(PercentChartView.DrawValue.PERCENT);
```
You can change the text of chart value. (default NONE)

```java
percentChart.setPercentChart(mSamples);
```
* *ArrayList<PercentChartData> percents* : items of percent chart

#### ++

```java
public void setLabelCount(int count)
```
You can change the count of X label. (default 5)

```java
public void showX(boolean show)
public void showLegend(boolean show)
public void showPercentUnit(boolean show)
```
You can show/hidden X, legend and percent unit.

#### PercentChartData
```java
new PercentChartData(R.color.color_chart_1, new LegendData("Korean"), 28800000)
```
* *@ColorRes int colorRes* : the background color of item
* *LegendData legend* : the legend of item
* *float value* : the value of item

#### LegendData
```java
public class LegendData {
    private String legend;
    private String value;
    private String subValue;
    private boolean showValue = true;
    private boolean showSubValue = true;
    ... 
}
```
* *String legend* : the title of legend
* *String value* : the value of legend
* *String subValue* : the subValue of legend


### TimeChartView

<img src="https://github.com/EunsilJo/TimePercentChart/blob/master/screenshots/5.png?raw=true" height="400"/> <img src="https://github.com/EunsilJo/TimePercentChart/blob/master/screenshots/6.png?raw=true" height="400"/>
```java
timeChart.setStartHour(6);
```
You can change start time. (default 0)

```java
timeChart.setTimeChart(getMillis("2017-11-10 00:00:00"), mSamples);
```
* *long date* : the reference date
* *ArrayList<TimeChartData> charts* : items of time chart

#### ++

```java
public void setLabelCount(int count)
```
You can change the count of X label. (default 5)

```java
public void showLegend(boolean show)
```
You can show/hidden legend.

#### TimeData
```java
new TimeData(getMillis("2017-11-10 06:00:00"), getMillis("2017-11-10 06:00:22"))
```
* *long startMills* : the start time of item
* *long stopMills* : the end time of item

#### TimeChartData
```java
new TimeChartData(R.color.color_chart_1, new LegendData("Plan"), values)
```
* *@ColorRes int colorRes* : the background color of item
* *LegendData legend* : the legend of item
* *ArrayList<TimeData> values* : items of time


### +
Please check the demo app to see examples.