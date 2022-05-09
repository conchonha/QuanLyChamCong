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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DsspAdapter extends RecyclerView.Adapter<ViewHolderDssp> implements Filterable {
    private OnClickEdit listener = null;

    private ArrayList<SanPham> listSp = new ArrayList<>();
    private List<SanPham> listSpNull = new ArrayList<>();


    public DsspAdapter(OnClickEdit listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderDssp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderDssp(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_ds_sp, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDssp holder, int position) {
        holder.bindData(listSp.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return listSp.size();
    }

    public void addData(ArrayList<SanPham> listSp) {
        this.listSp = listSp;
        listSpNull = new ArrayList<>(listSp);
    }

    //đây là hàm search sản phẩm theo tên có thể tham khảo filter trong recyclerview
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<SanPham> listSanPham = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                listSanPham.addAll(listSpNull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (SanPham sp : listSpNull) {
                    if (sp.getTenSP().toLowerCase().contains(filterPattern)) {
                        listSanPham.add(sp);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = listSanPham;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listSp.clear();
            listSp.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    //đây là interface sẽ lấy được sản phẩm mà mình click vd: khi mình click vào sp đầu tiên thì sẽ lấy đưuọc thông tin của sản phẩm đó
    public interface OnClickEdit {
        void edit(SanPham sanPham);
        void xoa(SanPham sanPham);
    }



}


class ViewHolderDssp extends RecyclerView.ViewHolder {
    public ViewHolderDssp(View view) {
        super(view);
    }

    TextView tv_ma;
    TextView tv_5;
    TextView tv_6;
    ImageView edit;
    ImageView xoa;
    ConstraintLayout itemsp;

    void bindData(SanPham sanPham, DsspAdapter.OnClickEdit listener) {
        tv_ma = itemView.findViewById(R.id.tv_ma);
        tv_5 = itemView.findViewById(R.id.tv_ten_sp_2);
        tv_6 = itemView.findViewById(R.id.tv_don_gia);
        edit = itemView.findViewById(R.id.edit);
        itemsp = itemView.findViewById(R.id.itemsp);
        xoa = itemView.findViewById(R.id.xoa);
        tv_ma.setText(sanPham.getMaSP());
        tv_5.setText(sanPham.getTenSP());
        tv_6.setText(String.valueOf(sanPham.getDonGia()));


        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener !=null){
                    listener.xoa(sanPham);

                }
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.edit(sanPham);
                }
            }
        });

    }
}

