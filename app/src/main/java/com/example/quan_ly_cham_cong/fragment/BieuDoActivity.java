package com.example.quan_ly_cham_cong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.model.ChiTietChamCong;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class BieuDoActivity extends AppCompatActivity {

    ArrayList barArraylist;
    AppDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = AppDatabase.getDatabaseInstance(this);
        setContentView(R.layout.activity_bieu_do);
        getData();
    }

    private void getData() {
        database.chiTietChamCongDAO().getListChiTietChamCongBieuDo().observe(this, (value -> {
            barArraylist = new ArrayList();

            Float x = 2f;
            for (ChiTietChamCong chitietChamcong : value) {
                barArraylist.add(new BarEntry(x++, chitietChamcong.getSoTP()));
            }
            loadData();
        }));
    }

    private void loadData() {
        BarChart barChart = findViewById(R.id.barchart);
        BarDataSet barDataSet = new BarDataSet(barArraylist, "nhân viên có thành phầm từ thấp đến cao");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        //color bar data set
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //text color
        barDataSet.setValueTextColor(Color.BLACK);
        //settting text size
        barDataSet.setValueTextSize(10f);
        barChart.getDescription().setEnabled(true);
        barChart.invalidate();
    }
}
