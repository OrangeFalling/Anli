package com.example.anli;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class PaiChartActivity extends AppCompatActivity {
    private PieChart mPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_chart);
        mPieChart = findViewById(R.id.pie_chart);  //初始化饼状图
        //初始化饼状图数据类
        PieData mPieData = getPieData();
        showChart(mPieChart,mPieData);
    }

    /**
     * 展示饼状图
     * @param mPieChart
     * @param mPieData
     */
    private void showChart(PieChart mPieChart, PieData mPieData) {
        //设置饼状图中间透明
        mPieChart.setHoleColor(Color.TRANSPARENT);
        //设置饼状图半径
        mPieChart.setHoleRadius(60f);
        //饼状图可以中间添加文字
        mPieChart.setDrawCenterText(true);
        // draw the pie center empty
        mPieChart.setDrawHoleEnabled(true);
        //
        mPieChart.setCenterText("家庭支出");
        //初始选择角度
        mPieChart.setRotationAngle(90);
        // enable the rotation / spinning of the chart by touch
        mPieChart.setRotationEnabled(true);
        // values inside the PieChart are drawn in percent and not with their original value.
        // Values provided for the IValueFormatter to format are then provided in percent.
        mPieChart.setUsePercentValues(true);
        // Sets a new data object for the chart
        mPieChart.setData(mPieData);
        // 设置比例图
        Legend legend = mPieChart.getLegend();
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(5f);

        //进入比例图，展开的动画
        mPieChart.animateXY(1000,1000);
    }

    /**
     * 饼状图数据类
     * @return pie chart data
     */
    private PieData getPieData() {
        //衣食住行以及其它
        ArrayList<String> xValue = new ArrayList<>();
        xValue.add("衣");
        xValue.add("食");
        xValue.add("住");
        xValue.add("行");
        xValue.add("其它");
        //保存每一个板块所占比例
        ArrayList<PieEntry> yValue = new ArrayList<>();
        float q1 = 10;
        float q2 = 20;
        float q3 = 25;
        float q4 = 40;
        float q5 = 5;
        yValue.add(new PieEntry(q1,0));
        yValue.add(new PieEntry(q2,1));
        yValue.add(new PieEntry(q3,2));
        yValue.add(new PieEntry(q4,3));
        yValue.add(new PieEntry(q5,4));
        PieDataSet pieDataSet = new PieDataSet(yValue,"202家庭支出");
        pieDataSet.setSliceSpace(1f);  //切片空间间隔
        //颜色数组
        ArrayList<Integer> pColors = new ArrayList<>();
        //饼状图颜色
        pColors.add(Color.RED);
        pColors.add(Color.BLUE);
        pColors.add(Color.GREEN);
        pColors.add(Color.GRAY);
        pColors.add(Color.YELLOW);
        //将颜色配置到饼状图
        pieDataSet.setColors(pColors);
        //设置圆盘文字颜色
        pieDataSet.setValueTextColor(Color.WHITE);
        //设置文字字号
        pieDataSet.setValueTextSize(15f);
        //设置显示数据的样式：百分比显示
        pieDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ""+ (int)value + "%";  //转换为整数
            }
        });
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px);
        PieData pieData = new PieData(pieDataSet);
        return pieData;
    }
}