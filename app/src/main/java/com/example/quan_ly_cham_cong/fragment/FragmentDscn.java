package com.example.quan_ly_cham_cong.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.adapter.DscnAdapter;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.model.CongNhan;

import java.util.ArrayList;


public class FragmentDscn extends AppCompatActivity implements DscnAdapter.OnClickEditCN {
    private DscnAdapter dscnAdapter;
    AppDatabase database;
    RecyclerView rcv_dscn;
    AppCompatButton them_cn;
    SearchView btn_search_cn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dscn);
        dscnAdapter = new DscnAdapter(this);
        database = AppDatabase.getDatabaseInstance(this);
        rcv_dscn = this.findViewById(R.id.rcv_dscn);
        dscnAdapter.addData((ArrayList<CongNhan>) database.congNhanDAO().getListCongNhan());
        rcv_dscn.setAdapter(dscnAdapter);
        rcv_dscn.setLayoutManager(new LinearLayoutManager(this));
        them_cn = this.findViewById(R.id.them_cn);
        btn_search_cn = this.findViewById(R.id.btn_search_cn);
        them_cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FragmentDscn.this, Fragment_Them.class));
            }
        });
        searchCn();
    }
    private void searchCn(){
        btn_search_cn.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                dscnAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dscnAdapter.addData((ArrayList<CongNhan>) database.congNhanDAO().getListCongNhan());
        rcv_dscn.setAdapter(dscnAdapter);
        rcv_dscn.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(CongNhan congNhan) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("cn", congNhan);
        startActivity(new Intent(this, Fragment_Snv.class).putExtras(bundle));
    }

    @Override
    public void xoacn(CongNhan congNhan) {
        new AlertDialog.Builder(this)
                .setTitle("Xóa công nhân")
                .setMessage("bạn muốn xóa công nhân")
                .setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.congNhanDAO().delete(congNhan);
                        dscnAdapter.addData((ArrayList<CongNhan>) database.congNhanDAO().getListCongNhan());
                        rcv_dscn.setAdapter(dscnAdapter);
                        dscnAdapter.notifyDataSetChanged();
                        rcv_dscn.setLayoutManager(new LinearLayoutManager(FragmentDscn.this));
                        Toast.makeText(FragmentDscn.this,"Đã xóa công nhân thành công" +congNhan.getTenCN(),Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();



    }


}