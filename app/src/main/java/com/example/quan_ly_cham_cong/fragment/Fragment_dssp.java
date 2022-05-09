package com.example.quan_ly_cham_cong.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.adapter.DsspAdapter;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.model.SanPham;


import java.util.ArrayList;

public class Fragment_dssp extends Activity implements DsspAdapter.OnClickEdit {

    private DsspAdapter dsspAdapter;
    RecyclerView rcv_dssp;
    AppCompatButton them_sp;
    AppDatabase database;
    SearchView btn_search;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmen_ds_sp);
        dsspAdapter = new DsspAdapter(this);
        database = AppDatabase.getDatabaseInstance(this);
        dsspAdapter.addData((ArrayList<SanPham>) database.sanPhamDAO().getListSP());
        rcv_dssp = this.findViewById(R.id.rcv_dssp);
        rcv_dssp.setAdapter(dsspAdapter);
        dsspAdapter.notifyDataSetChanged();
        rcv_dssp.setLayoutManager(new LinearLayoutManager(this));
        them_sp = this.findViewById(R.id.themsp);
        //đoạn này là click sẽ sang màn edit sản phẩmc

        them_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Fragment_dssp.this, Fragmen_themsp.class);
                startActivity(intent);
            }
        });
        searchSp();
    }

    //sang bên này thì mình phải implement interface đó và override lại hàm này rồi thực hiện thao tác gửi dữ liệu sang màn hình để edit sản phẩm
    @Override
    public void edit(SanPham sanPham) {
        //đoạn này gói dữ liệu vào trong bundel rồi gửi sang bên kia
        Bundle bundle = new Bundle();
        bundle.putSerializable("san_pham", sanPham);
        //thông qua cái này
        Intent intent = new Intent(Fragment_dssp.this, Fragment_edit_sp.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void xoa(SanPham sanPham) {
        new AlertDialog.Builder(this)
                .setTitle("Xóa Sản Phẩm")
                .setMessage("bạn muốn xóa")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.sanPhamDAO().deleteSp(sanPham);
                        dsspAdapter.addData((ArrayList<SanPham>) database.sanPhamDAO().getListSP());
                        rcv_dssp.setAdapter(dsspAdapter);
                        dsspAdapter.notifyDataSetChanged();
                        rcv_dssp.setLayoutManager(new LinearLayoutManager(Fragment_dssp.this));
                        Toast.makeText(Fragment_dssp.this, "Đã xóa thành công sản phẩm " + sanPham.getTenSP(), Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();

    }

    private void searchSp(){
        btn_search = this.findViewById(R.id.btn_search_sp);
        btn_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                 dsspAdapter.getFilter().filter(newText);
                 return false;
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        dsspAdapter.addData((ArrayList<SanPham>) database.sanPhamDAO().getListSP());
        rcv_dssp.setAdapter(dsspAdapter);
        dsspAdapter.notifyDataSetChanged();
    }
}
