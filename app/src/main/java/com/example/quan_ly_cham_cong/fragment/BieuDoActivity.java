package com.example.quan_ly_cham_cong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.model.ChiTietChamCong;
import com.example.quan_ly_cham_cong.model.CongNhan;
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
    LinearLayout gridLayout;
    private List<Pair<Integer,String>>listNameCongNhan = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = AppDatabase.getDatabaseInstance(this);
        setContentView(R.layout.activity_bieu_do);
        gridLayout = findViewById(R.id.gridLayout);
        getData();
    }

    private void getData() {
        barArraylist = new ArrayList();
        Float x = 2f;
        List<CongNhan>listCongNhan = database.congNhanDAO().getListCongNhan();
        try {
            for (CongNhan congNhan : listCongNhan){
                if(congNhan.getChamCong() != null && congNhan.getChamCong().getChiTietChamCong() != null){
                    ChiTietChamCong chiTietChamCong = congNhan.getChamCong().getChiTietChamCong();
                    listNameCongNhan.add(new Pair((chiTietChamCong.getSoTP() - chiTietChamCong.getSoPP()),congNhan.getTenCN()));
                    barArraylist.add(new BarEntry(x++, (chiTietChamCong.getSoTP() - chiTietChamCong.getSoPP())));
                }
            }
            Log.d("AAA", "getData: "+listNameCongNhan);
            loadData();

            if(listNameCongNhan != null){
                for (int i = 0; i < listNameCongNhan.size(); i++){
                    View view = View.inflate(gridLayout.getContext(),R.layout.item_bieu_do,null);
                    TextView txtName = view.findViewById(R.id.txtNameCongNhan);
                    TextView txtSoSPTB = view.findViewById(R.id.txtSoSPTB);

                    txtName.setText(listNameCongNhan.get(i).second +": ");
                    txtSoSPTB.setText(listNameCongNhan.get(i).first.toString());
                    gridLayout.addView(view);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
