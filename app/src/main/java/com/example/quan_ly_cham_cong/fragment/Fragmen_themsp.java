package com.example.quan_ly_cham_cong.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.database.SanPhamDAO;
import com.example.quan_ly_cham_cong.model.SanPham;

public class Fragmen_themsp extends Activity {
    EditText tv_ten2, tv_sp3, ten_masp, masp;
    AppCompatButton btn_luu;
    AppDatabase database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmen_themsp);
        database = AppDatabase.getDatabaseInstance(this);
        tv_ten2 = this.findViewById(R.id.tv_ten2);
        tv_sp3 = this.findViewById(R.id.tv_sp3);
        btn_luu = this.findViewById(R.id.luu);
        masp = this.findViewById(R.id.ten_masp);
        queryDatabase();
    }

    private void queryDatabase(){
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSP(masp.getText().toString());
                sanPham.setDonGia(Float.parseFloat(tv_sp3.getText().toString()));
                sanPham.setTenSP(tv_ten2.getText().toString());
                database.sanPhamDAO().insertSanPham(sanPham);
                Toast.makeText(Fragmen_themsp.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                Log.d("`toanpk_dssp`", String.valueOf(database.sanPhamDAO().getListSP().size()));
            }
        });
    }



}
