package com.example.quan_ly_cham_cong.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.model.CongNhan;


public class FragmentEditCC extends AppCompatActivity {
    private CongNhan congNhan;
    TextView tv_macn_2, tv_masp_2, thanh_pham, phepham;
    ImageView img_top1, btn_dow1, img_top,Img_dow;
    AppCompatButton luu, huy;
    AppDatabase database;
    private int countTP;
    private int countPP;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_c_c);
        congNhan = (CongNhan) getIntent().getExtras().getSerializable("congNhan");
        tv_macn_2 = this.findViewById(R.id.tv_macn_2);
        tv_masp_2 = this.findViewById(R.id.tv_masp_2);
        thanh_pham = this.findViewById(R.id.thanh_pham);
        phepham = this.findViewById(R.id.phepham);
        img_top1 = this.findViewById(R.id.img_top1);
        btn_dow1 = this.findViewById(R.id.btn_dow1);
        img_top = this.findViewById(R.id.img_top);
        Img_dow = this.findViewById(R.id.Img_dow);
        luu = this.findViewById(R.id.luu);
        huy = this.findViewById(R.id.huy);
        database = AppDatabase.getDatabaseInstance(this);
        countTPAndPP();
        initData();
        editCC();
    }

    private void initData() {
        tv_macn_2.setText(congNhan.getMaCN());
        if(congNhan.getChamCong()!= null){
            tv_masp_2.setText(congNhan.getChamCong().getChiTietChamCong().getMaSP());
            thanh_pham.setText(congNhan.getChamCong().getChiTietChamCong().getSoTP()+"");
            phepham.setText(congNhan.getChamCong().getChiTietChamCong().getSoPP()+"");
        }
    }

    private void editCC(){
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (congNhan.getChamCong() != null){
                    if (congNhan.getChamCong().getChiTietChamCong() != null){
                        congNhan.setMaCN(tv_macn_2.getText().toString());
                        congNhan.getChamCong().getChiTietChamCong().setSoTP(Integer.parseInt(thanh_pham.getText().toString()));
                        congNhan.getChamCong().getChiTietChamCong().setSoPP(Integer.parseInt(phepham.getText().toString()));
                        database.congNhanDAO().updateCongNhan(congNhan);
                        Toast.makeText(FragmentEditCC.this, "Sửa Chấm công thành công!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(FragmentEditCC.this, "Sửa thất bại vì công nhân " + congNhan.getTenCN() + " chưa chấm công!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        huy.setOnClickListener((v->{
            finish();
        }));

        findViewById(R.id.back_edt).setOnClickListener((view -> {
            finish();
        }));
    }

    private void countTPAndPP(){
        if (congNhan.getChamCong() == null){
                countTP = 0;
                countPP = 0;
                thanh_pham.setText("0");
                phepham.setText("0");
        }else {
            countTP = congNhan.getChamCong().getChiTietChamCong().getSoTP();
            countPP = congNhan.getChamCong().getChiTietChamCong().getSoPP();
            thanh_pham.setText(Integer.toString(countTP));
            phepham.setText(Integer.toString(countPP));
        }
        img_top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countTP++;
                thanh_pham.setText(Integer.toString(countTP));
            }
        });
        btn_dow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countTP > 0){
                    countTP--;
                    thanh_pham.setText(Integer.toString(countTP));
                }
            }
        });

        img_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countPP++;
                phepham.setText(Integer.toString(countPP));
            }
        });
        Img_dow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countPP > 0){
                    countPP--;
                    phepham.setText(Integer.toString(countPP));
                }
            }
        });
    }


}