package com.example.quan_ly_cham_cong.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.model.ChamCong;
import com.example.quan_ly_cham_cong.model.CongNhan;

public class Fragment_Them extends Activity {
    TextView nameCongNhan, maCongNhan, phanXuong;
    AppCompatButton button_luu, btn_huy;
    AppDatabase database;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmen_themcn);
        database = AppDatabase.getDatabaseInstance(this);
        maCongNhan = this.findViewById(R.id.tv_4);
        nameCongNhan = this.findViewById(R.id.tv_5);
        phanXuong = this.findViewById(R.id.tv_6);
        button_luu = this.findViewById(R.id.luu);
        btn_huy = this.findViewById(R.id.huy);
        insertDatabase();
    }


    private void insertDatabase(){
        button_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CongNhan congNhan = new CongNhan();
                congNhan.setMaCN(maCongNhan.getText().toString());
                congNhan.setTenCN(nameCongNhan.getText().toString());
                congNhan.setPhanXuong(phanXuong.getText().toString());
                database.congNhanDAO().insert(congNhan);
                Toast.makeText(Fragment_Them.this, "Thêm công nhân thành công!", Toast.LENGTH_SHORT).show();
                Log.d("toanpk", String.valueOf(database.congNhanDAO().getListCongNhan().size()));
            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Fragment_Them.this.onBackPressed();
            }
        });
    }


}
