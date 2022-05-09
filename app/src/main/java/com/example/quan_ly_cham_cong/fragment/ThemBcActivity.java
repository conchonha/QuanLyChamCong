package com.example.quan_ly_cham_cong.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.model.BaoCao;

public class ThemBcActivity extends AppCompatActivity {
    AppDatabase database;
    EditText name, phanXuong, thanhPham, aoSoMiNam, aoSoMiNu, baoTay, dep, non, quanTay;
    AppCompatButton btn_luu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_bc);
        database = AppDatabase.getDatabaseInstance(this);
        insertData();
    }

    private void insertData(){
        btn_luu = this.findViewById(R.id.btn_luu);
        name = this.findViewById(R.id.edt_name);
        phanXuong = this.findViewById(R.id.edt_phanxuon);
        thanhPham = this.findViewById(R.id.edt_tp);
        aoSoMiNam = this.findViewById(R.id.edt_smNam);
        aoSoMiNu = this.findViewById(R.id.edt_smNu);
        baoTay = this.findViewById(R.id.edt_baotay);
        dep = this.findViewById(R.id.edt_dep);
        quanTay = this.findViewById(R.id.edt_quantay);
        non = this.findViewById(R.id.edt_non);
        thanhPham = this.findViewById(R.id.edt_tp);
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaoCao baoCao = new BaoCao();
                baoCao.setTen(name.getText().toString());
                baoCao.setPhanXuong(phanXuong.getText().toString());
                baoCao.setTP(Integer.parseInt(thanhPham.getText().toString()));
                baoCao.setSlAoSoMiNam(Integer.parseInt(aoSoMiNam.getText().toString()));
                baoCao.setSlAoSoMiNu(Integer.parseInt(aoSoMiNu.getText().toString()));
                baoCao.setSlBaoTay(Integer.parseInt(baoTay.getText().toString()));
                baoCao.setSlDep(Integer.parseInt(dep.getText().toString()));
                baoCao.setSlQuan(Integer.parseInt(quanTay.getText().toString()));
                baoCao.setSlNon(Integer.parseInt(non.getText().toString()));
                database.baoCaoDAO().insert(baoCao
                );
            }
        });
    }
}