package com.example.quan_ly_cham_cong.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.fragment.BaoCaoActivity;
import com.example.quan_ly_cham_cong.fragment.Fragmen_dscc;
import com.example.quan_ly_cham_cong.fragment.FragmentDscn;
import com.example.quan_ly_cham_cong.fragment.Fragment_dssp;
;

public class MainActivity extends AppCompatActivity {

    AppCompatButton btn_ds, btn_bttcc, btn_ds_sp,btn_bc,btn_xem_bieu_do;
    ConstraintLayout constrain_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        eventClick();
    }

    private void anhXa(){
        btn_ds = this.findViewById(R.id.btn_ds_cn);
        constrain_main = this.findViewById(R.id.constrain_main);
        btn_bttcc = this.findViewById(R.id.btn_bttcc);
        btn_ds_sp = this.findViewById(R.id.btn_ds_sp);
        btn_bc = this.findViewById(R.id.btn_bc);

    }

    private void eventClick(){
        btn_ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, FragmentDscn.class);
               startActivity(intent);
            }
        });

        btn_bttcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Fragmen_dscc.class);
                startActivity(intent);
            }
        });

        btn_ds_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Fragment_dssp.class);
                startActivity(intent);
            }
        });

        btn_bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BaoCaoActivity.class);
                startActivity(intent);
            }
        });

    }



}