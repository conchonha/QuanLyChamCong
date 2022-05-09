package com.example.quan_ly_cham_cong.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
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
import com.example.quan_ly_cham_cong.model.ChamCong;
import com.example.quan_ly_cham_cong.model.CongNhan;

import java.util.ArrayList;
import java.util.List;

public class DsccAdapter extends RecyclerView.Adapter<ViewHolderDSCC> implements Filterable {
    private OnClickEditCC listener;
    private ArrayList<CongNhan> listCN = new ArrayList<>();
    private ArrayList<CongNhan> listCnNull = new ArrayList<>();
    public DsccAdapter(OnClickEditCC listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolderDSCC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderDSCC(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cc, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDSCC holder, int position) {
        if (listener != null){
            holder.bindData(listCN.get(position), listener);
        }
    }

    @Override
    public int getItemCount() {
        return listCN.size();
    }

    public void addData(ArrayList<CongNhan> listCongNhan){
        this.listCN = listCongNhan;
        this.listCnNull = new ArrayList<>(listCongNhan);
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
            listCN.clear();
            listCN.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public interface OnClickEditCC{
        void onClick(CongNhan congNhan);
    }
}


class ViewHolderDSCC extends RecyclerView.ViewHolder{
    public ViewHolderDSCC(View view){
        super(view);
    }
    TextView tv_ma_cc, tv_ngay_cc, tv_ma_cn, tv_ten_cn, tv_ma_sp, tv_ten_sp, tv_so_pp, tv_so_tp;
    ImageView edt_cc;
    @SuppressLint("SetTextI18n")
    void bindData(CongNhan congNhan, DsccAdapter.OnClickEditCC listener){
        tv_ma_cc = itemView.findViewById(R.id.tv_ma_cc);
        tv_ngay_cc = itemView.findViewById(R.id.tv_ngay_cc);
        tv_ma_cn = itemView.findViewById(R.id.tv_ma_cn);
        tv_ten_cn = itemView.findViewById(R.id.tv_ten_cn);
        tv_so_pp = itemView.findViewById(R.id.tv_so_pp);
        tv_so_tp = itemView.findViewById(R.id.tv_so_tp);
        tv_ma_cn = itemView.findViewById(R.id.tv_ma_cn);
        tv_ten_cn = itemView.findViewById(R.id.tv_ten_cn);
        edt_cc = itemView.findViewById(R.id.edt_cc);
        tv_ma_cn.setText("Mã công nhân: " + congNhan.getMaCN());
        tv_ten_cn.setText("Tên công nhân: " + congNhan.getTenCN());
        if (congNhan.getChamCong() != null){
                tv_ma_cc.setText("Mã chấm công: " + congNhan.getChamCong().getMaCC());
                tv_ngay_cc.setText("Ngày: " + congNhan.getChamCong().getNgayCC());
            if (congNhan.getChamCong().getChiTietChamCong() != null){
                    tv_so_tp.setText("Số thành phẩm: " + congNhan.getChamCong().getChiTietChamCong().getSoTP());
                    tv_so_pp.setText("Số phế phẩm: " + congNhan.getChamCong().getChiTietChamCong().getSoPP());
                }else {
                tv_so_tp.setVisibility(View.GONE);
                tv_so_pp.setVisibility(View.GONE);
            }
            }else {
                tv_ma_cc.setVisibility(View.GONE);
                tv_ngay_cc.setVisibility(View.GONE);
            }

        Log.d("AAA", "bindData: "+congNhan.getMaCN());
        edt_cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onClick(congNhan);
                }
            }
        });
        }
    }

