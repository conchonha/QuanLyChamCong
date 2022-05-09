package com.example.quan_ly_cham_cong.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.fragment.BaoCaoNewActivity;
import com.example.quan_ly_cham_cong.model.CongNhan;
import com.example.quan_ly_cham_cong.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DscnAdapter extends RecyclerView.Adapter<ViewHolderDscn> implements Filterable{
    private OnClickEditCN listener;
    private ArrayList<CongNhan> listCn = new ArrayList<>();
    private ArrayList<CongNhan> listCnNull = new ArrayList<>();
    private int baocao = 0;

    public DscnAdapter(OnClickEditCN listener,int baocaoValue){
        this.listener = listener;
        this.baocao = baocaoValue;
    }
    @NonNull
    @Override
    public ViewHolderDscn onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderDscn(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dscn, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDscn holder, int position) {
        holder.bindData(listCn.get(position), listener,baocao);
    }

    @Override
    public int getItemCount() {
        return listCn.size();
    }

    public void addData(ArrayList<CongNhan> listSp){
        this.listCn = listSp;
        this.listCnNull = new ArrayList<>(listSp);
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<CongNhan> listCongNhan= new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                listCongNhan.addAll(listCnNull);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (CongNhan sp : listCnNull){
                    if (sp.getTenCN().toLowerCase().contains(filterPattern)){
                        listCongNhan.add(sp);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = listCongNhan;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listCn.clear();
            listCn.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public interface OnClickEditCN{
        void onClick(CongNhan congNhan);
        void xoacn(CongNhan congNhan);
        void baocao(CongNhan congNhan);
    }
}


class ViewHolderDscn extends RecyclerView.ViewHolder {
    public ViewHolderDscn(View view) {
        super(view);
    }
    TextView tv_ten_cn, tv_ma_cn, tv_phan_xuong;
    ImageView btn_edit_cn ,xoa,img_bao_cao;

    void bindData(CongNhan congNhan, DscnAdapter.OnClickEditCN listener,int baocao) {
        tv_ten_cn = itemView.findViewById(R.id.tv_ten_cn_ds);
        tv_ma_cn = itemView.findViewById(R.id.tv_ma_cn_ds);
        tv_phan_xuong = itemView.findViewById(R.id.tv_phan_xuong);
        btn_edit_cn = itemView.findViewById(R.id.edt_cn);
        xoa = itemView.findViewById(R.id.img_xoacn);
        img_bao_cao = itemView.findViewById(R.id.img_bao_cao);
        if(baocao == BaoCaoNewActivity.BAO_CAO_VALUE){
            btn_edit_cn.setVisibility(View.GONE);
            xoa.setVisibility(View.GONE);
        }else{
            img_bao_cao.setVisibility(View.GONE);
        }

        if (congNhan.getPhanXuong() == null){
            tv_ma_cn.setText("Mã công nhân: " + congNhan.getMaCN());
            tv_ten_cn.setText("Tên công nhân: " + congNhan.getTenCN());
            tv_phan_xuong.setVisibility(View.GONE);
        }else {
            tv_ma_cn.setText("Mã công nhân: " + congNhan.getMaCN());
            tv_ten_cn.setText("Tên công nhân: " + congNhan.getTenCN());
            tv_phan_xuong.setText("Phân xưởng: " + congNhan.getPhanXuong());
        }
        btn_edit_cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onClick(congNhan);
                }
            }
        });
        img_bao_cao.setOnClickListener((v)->{
            if(listener !=null){
                listener.baocao(congNhan);
            }
        });

        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener !=null){
                    listener.xoacn(congNhan);
                }
            }
        });

    }


}

