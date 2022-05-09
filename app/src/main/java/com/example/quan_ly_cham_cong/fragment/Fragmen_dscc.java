package com.example.quan_ly_cham_cong.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.adapter.DsccAdapter;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.main.MainActivity;
import com.example.quan_ly_cham_cong.model.ChamCong;
import com.example.quan_ly_cham_cong.model.CongNhan;

import java.util.ArrayList;

public class Fragmen_dscc extends AppCompatActivity implements DsccAdapter.OnClickEditCC {

    private DsccAdapter adapter;
    private ArrayList<CongNhan> listCN = new ArrayList<>();
    RecyclerView rcv_ds_cc;
    SearchView btn_search_cc;
    AppCompatButton btn_add;
    EditText edt_ma_cc;
    EditText edt_ngay_cc;
    EditText edt_ma_cn;
    AppDatabase database;
    AppCompatButton them_cc;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ds_cc);
        adapter = new DsccAdapter(this);
        adapter.notifyDataSetChanged();
        database = AppDatabase.getDatabaseInstance(this);
        btn_add = this.findViewById(R.id.themcn);
        edt_ma_cc = this.findViewById(R.id.edt_ma_cc);
        edt_ngay_cc = this.findViewById(R.id.edt_ngay_cc);
        edt_ma_cn = this.findViewById(R.id.edt_ma_cn);
        rcv_ds_cc = this.findViewById(R.id.rcv_dssp);
        them_cc = this.findViewById(R.id.them_cc);
        btn_search_cc = findViewById(R.id.btn_search_cc);
        addData();
        them_cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Fragmen_dscc.this, FragmentThemCC.class);
                startActivity(intent);
            }
        });
        searchCC();
    }


    private void addData() {
        if (database.sanPhamDAO().getListSP() != null) {
            if (database.chiTietChamCongDAO().getListChiTietChamCong() != null) {
                if (database.chamCongDAO().listChamCong() != null) {
                    if (database.congNhanDAO().getListCongNhan() != null) {
                        adapter.addData((ArrayList<CongNhan>) database.congNhanDAO().getListCongNhan());
                        rcv_ds_cc.setAdapter(adapter);
                        rcv_ds_cc.setLayoutManager(new LinearLayoutManager(this));
                    }
                }
            }
        }
    }

    private void searchCC() {
        btn_search_cc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    @Override
    public void onClick(CongNhan congNhan) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("congNhan", congNhan);
        startActivity(new Intent(Fragmen_dscc.this, FragmentEditCC.class).putExtras(bundle));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (database.sanPhamDAO().getListSP() != null) {
            if (database.chiTietChamCongDAO().getListChiTietChamCong() != null) {
                if (database.chamCongDAO().listChamCong() != null) {
                    if (database.congNhanDAO().getListCongNhan() != null) {
                        adapter.addData((ArrayList<CongNhan>) database.congNhanDAO().getListCongNhan());
                        rcv_ds_cc.setAdapter(adapter);
                        rcv_ds_cc.setLayoutManager(new LinearLayoutManager(this));
                    }
                }
            }
        }
    }
}
