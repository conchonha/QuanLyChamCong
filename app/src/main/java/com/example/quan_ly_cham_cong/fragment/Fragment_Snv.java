package com.example.quan_ly_cham_cong.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.model.CongNhan;

public class Fragment_Snv extends Activity {
    EditText tv_4, tv_5, tv_6;
    AppCompatButton luu, huy;
    private CongNhan congNhan;
    private AppDatabase database;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmen_edit_cn);
        tv_4 = findViewById(R.id.tv_4);
        tv_5 = findViewById(R.id.tv_5);
        tv_6 = findViewById(R.id.tv_6);
        luu = findViewById(R.id.luu);
        huy = findViewById(R.id.huy);
        Bundle bundle = new Bundle();
        database = AppDatabase.getDatabaseInstance(this);
        congNhan = (CongNhan) getIntent().getExtras().getSerializable("cn");

        initData();
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                congNhan.setTenCN(tv_5.getText().toString());
                congNhan.setMaCN(tv_4.getText().toString());
                congNhan.setPhanXuong(tv_6.getText().toString());
                database.congNhanDAO().updateCongNhan(congNhan);
                Toast.makeText(Fragment_Snv.this, "Sửa công nhân thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        huy.setOnClickListener((view -> {
            finish();
        }));
    }

    private void initData() {
        if(congNhan != null){
            tv_4.setText(congNhan.getMaCN());
            tv_5.setText(congNhan.getTenCN());
            tv_6.setText(congNhan.getPhanXuong());
        }
    }
}
