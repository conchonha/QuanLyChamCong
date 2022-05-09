package com.example.quan_ly_cham_cong.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.model.SanPham;

public class Fragment_edit_sp extends Activity {
    EditText tv_masp, tv_tensp2,dongia;
    AppCompatButton btn_luu, btn_huy;
    AppDatabase database;
    private SanPham sanPham;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_sp);
        database = AppDatabase.getDatabaseInstance(this);
        tv_masp = this.findViewById(R.id.tv_masp2);
        tv_tensp2 = this.findViewById(R.id.tv_tensp2);
        dongia = this.findViewById(R.id.dongia);
        btn_luu = this.findViewById(R.id.luu_edit_sp);
        btn_huy = findViewById(R.id.huy);
        sanPham = (SanPham) getIntent().getExtras().getSerializable("san_pham");

        initData();
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSP();
                Toast.makeText(Fragment_edit_sp.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_edit_sp.this.onBackPressed();
            }
        });

        findViewById(R.id.back_edt).setOnClickListener((view -> {
            finish();
        }));
    }

    private void initData() {
        if(sanPham != null){
            tv_masp.setText(sanPham.getMaSP());
            tv_tensp2.setText(sanPham.getTenSP());
            dongia.setText(sanPham.getDonGia()+"");
        }
    }


    private void updateSP() {
         sanPham.setMaSP(tv_masp.getText().toString());
         sanPham.setTenSP(tv_tensp2.getText().toString());
         sanPham.setDonGia(Float.parseFloat(dongia.getText().toString()));
         database.sanPhamDAO().updateSP(sanPham);
    }
}


