package com.example.quan_ly_cham_cong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.model.BaoCao;

import java.util.ArrayList;
import java.util.List;

public class BaoCaoApdater extends RecyclerView.Adapter<BaoCaoViewHolder> {
    private List<BaoCao> listBaoCao;
    @NonNull
    @Override
    public BaoCaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaoCaoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_bao_cao, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BaoCaoViewHolder holder, int position) {
        holder.binData(listBaoCao.get(position));
    }

    @Override
    public int getItemCount() {
        return listBaoCao.size();
    }

    public void addBaoCao(List<BaoCao> listBaoCao){
        this.listBaoCao = listBaoCao;
    }
}

class BaoCaoViewHolder extends RecyclerView.ViewHolder{
    public BaoCaoViewHolder(View view){
        super(view);
    }
    TextView ten, phanXuong, soThanhPham, soAoSoMiNam, soAosoMiNu,soBaoTay,soDep,soNon,soQuanTay ;

    void binData(BaoCao baoCao){
        ten = itemView.findViewById(R.id.name);
        phanXuong = itemView.findViewById(R.id.px);
        soThanhPham = itemView.findViewById(R.id.thanhp);
        soAoSoMiNam = itemView.findViewById(R.id.aonam);
        soAosoMiNu = itemView.findViewById(R.id.aonu);
        soBaoTay = itemView.findViewById(R.id.baotay);
        soDep = itemView.findViewById(R.id.dep);
        soNon = itemView.findViewById(R.id.non);
        soQuanTay = itemView.findViewById(R.id.quan);

        ten.setText(baoCao.getTen());
        phanXuong.setText(baoCao.getPhanXuong());
        soThanhPham.setText(String.valueOf(baoCao.getTP()));
        soAoSoMiNam.setText(String.valueOf(baoCao.getSlAoSoMiNam()));
        soAosoMiNu.setText(String.valueOf(baoCao.getSlAoSoMiNu()));
        soBaoTay.setText(String.valueOf(baoCao.getSlBaoTay()));
        soDep.setText(String.valueOf(baoCao.getSlDep()));
        soNon.setText(String.valueOf(baoCao.getSlNon()));
        soQuanTay.setText(String.valueOf(baoCao.getSlQuan()));

    }

}
