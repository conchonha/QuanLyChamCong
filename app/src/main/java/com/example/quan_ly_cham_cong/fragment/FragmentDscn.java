package com.example.quan_ly_cham_cong.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.util.Log;
import android.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.Toast;


import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.adapter.DscnAdapter;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.model.ChiTietChamCong;
import com.example.quan_ly_cham_cong.model.SanPham;
import com.example.quan_ly_cham_cong.network.APIServices;
import com.example.quan_ly_cham_cong.network.ApiIpList;
import com.example.quan_ly_cham_cong.model.CongNhan;
import com.example.quan_ly_cham_cong.model.ResultGoogleSheet;
import com.example.quan_ly_cham_cong.utils.Helper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentDscn extends AppCompatActivity implements DscnAdapter.OnClickEditCN {
    private DscnAdapter dscnAdapter;
    AppDatabase database;
    RecyclerView rcv_dscn;
    AppCompatButton them_cn;
    SearchView btn_search_cn;
    private Helper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dscn);
        helper = new Helper(this);
        int intent = getIntent().getIntExtra(BaoCaoNewActivity.BAO_CAO_KEY, 0);
        dscnAdapter = new DscnAdapter(this, intent);
        database = AppDatabase.getDatabaseInstance(FragmentDscn.this);
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

    private void searchCn() {
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
                        Toast.makeText(FragmentDscn.this, "Đã xóa công nhân thành công" + congNhan.getTenCN(), Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();

    }

    @Override
    public void baocao(CongNhan congNhan) {
        String maCC = "null";
        String ngayCC = "null";
        int soSP = 0;
        String soPP = "null";
        String tienCong = "null";
        String thanhTien = "null";

       try {
           helper.showProgressLoading();
           if (congNhan.getChamCong() != null) {
               maCC = congNhan.getChamCong().getMaCC();
               ngayCC = congNhan.getChamCong().getNgayCC();
           }

           if (congNhan.getChamCong() != null && congNhan.getChamCong().getChiTietChamCong() != null) {
               ChiTietChamCong chiTietChamCong = congNhan.getChamCong().getChiTietChamCong();
               soSP = chiTietChamCong.getSoTP();
               soPP = chiTietChamCong.getSoPP() + "";

               for (SanPham sanPham : congNhan.getChamCong().getChiTietChamCong().getListSanPham()) {
                   String maSP = sanPham.getMaSP();
                   String tenSP = sanPham.getTenSP();
                   tienCong = sanPham.getDonGia() +"";
                   thanhTien = (sanPham.getDonGia() * soSP ) +"";
                   postDataGoogleSheet(maCC, ngayCC, maSP, tenSP, soSP+"", soPP, tienCong, thanhTien);
               }
           } else {
               postDataGoogleSheet(maCC, ngayCC, "null", "null", soSP+"", soPP, tienCong, thanhTien);
           }
       }catch (Exception e){
           helper.dismisProgess();
           Toast.makeText(this, "Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
       }
    }

    private void postDataGoogleSheet(String maCC, String ngayCC, String maSP, String tenSP, String soSP, String soPP, String tienCong, String thanhTien) {
        ApiIpList dataService = APIServices.getService();
        Call<ResultGoogleSheet> callback = dataService.postGoogleSheet("postBaoCao", maCC, ngayCC, maSP, tenSP, soSP, soPP, tienCong, thanhTien);
        callback.enqueue(new Callback<ResultGoogleSheet>() {
            @Override
            public void onResponse(Call<ResultGoogleSheet> call, Response<ResultGoogleSheet> response) {
                helper.dismisProgess();
                startActivity(new Intent(FragmentDscn.this,LoadBaoCaoWebView.class));
            }

            @Override
            public void onFailure(Call<ResultGoogleSheet> call, Throwable t) {
                helper.dismisProgess();
            }
        });
    }

}