package com.example.quan_ly_cham_cong.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quan_ly_cham_cong.R;

public class BaoCaoNewActivity extends AppCompatActivity {
    public static String BAO_CAO_KEY= "BAO_CAO_KEY";
    public static int BAO_CAO_VALUE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_cao_new);

        findViewById(R.id.bieudo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaoCaoNewActivity.this, BieuDoActivity.class));
            }
        });

        findViewById(R.id.bao_cao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaoCaoNewActivity.this,FragmentDscn.class);
                intent.putExtra(BAO_CAO_KEY,BAO_CAO_VALUE);
               startActivity(intent);
            }
        });
    }
}