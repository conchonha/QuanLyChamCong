package com.example.quan_ly_cham_cong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.database.ChiTietChamCongDAO;
import com.example.quan_ly_cham_cong.model.ChamCong;
import com.example.quan_ly_cham_cong.model.ChiTietChamCong;
import com.example.quan_ly_cham_cong.model.CongNhan;

public class FragmentThemCC extends AppCompatActivity {
    EditText tv_ngay_cc, tv_ma_cc,edt_thanhpham,edt_phepham,edt_tencn,edt_ma_cn,edt_masp,ten_sp_cc;
    AppDatabase database;
    AppCompatButton luu_cc,huy_cc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_them_cc);
        database = AppDatabase.getDatabaseInstance(this);
        tv_ma_cc = this.findViewById(R.id.tv_ma_cc);
        tv_ngay_cc = this.findViewById(R.id.tv_ngay_cc);
        luu_cc = this.findViewById(R.id.luu_cc);
        huy_cc = this.findViewById(R.id.huy_cc);
        edt_phepham = this.findViewById(R.id.edt_phepham);
        edt_thanhpham = this.findViewById(R.id.edt_thanhpham);
        edt_tencn = this.findViewById(R.id.edt_tencn_cc);
        edt_ma_cn = this.findViewById(R.id.edt_ma_cn_cc);
        luu_cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChamCong chamCong = new ChamCong();
                chamCong.setMaCN(edt_ma_cn.getText().toString());
                chamCong.setMaCC(tv_ma_cc.getText().toString());
                chamCong.setNgayCC(tv_ngay_cc.getText().toString());
                ChiTietChamCong chiTietChamCong = new ChiTietChamCong();
                chiTietChamCong.setMaCC(tv_ma_cc.getText().toString());
                chiTietChamCong.setSoTP(Integer.parseInt(edt_thanhpham.getText().toString()));
                chiTietChamCong.setSoPP(Integer.parseInt(edt_phepham.getText().toString()));
                chiTietChamCong.setMaCC(tv_ma_cc.getText().toString());
                chiTietChamCong.setListSanPham(database.sanPhamDAO().getListSP());
                database.chiTietChamCongDAO().insert(chiTietChamCong);
                chamCong.setChiTietChamCong(chiTietChamCong);
                database.chamCongDAO().insertChamCong(chamCong);
                CongNhan congNhan = new CongNhan();
                congNhan.setTenCN(edt_tencn.getText().toString());
                congNhan.setMaCN(edt_ma_cn.getText().toString());
                congNhan.setChamCong(chamCong);
                database.congNhanDAO().insert(congNhan);
                Toast.makeText(FragmentThemCC.this, "Đã thêm chấm công!", Toast.LENGTH_SHORT).show();
            }
        });
        huy_cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentThemCC.this.onBackPressed();
            }
        });
    }



}
