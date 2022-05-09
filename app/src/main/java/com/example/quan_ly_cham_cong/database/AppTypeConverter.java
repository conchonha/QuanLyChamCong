package com.example.quan_ly_cham_cong.database;



import androidx.room.TypeConverter;

import com.example.quan_ly_cham_cong.model.ChamCong;
import com.example.quan_ly_cham_cong.model.ChiTietChamCong;
import com.example.quan_ly_cham_cong.model.CongNhan;
import com.example.quan_ly_cham_cong.model.SanPham;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

//vì sqlite room chỉ nhận kiểu dữ liệu nguyên thủy không chấp nhận kiểu dữ liệu khác làm thành phần trong bảng
//nên phải convert kiểu dữ liệu dùng TypeConverter có sẵn trong room database kết hợp với thư viện Gson
//convert các kiểu dữ liệu khác ngoài (int, string, long,...)
public class AppTypeConverter {

    @TypeConverter
    public String fromChamCongToList(ChamCong value){
        Gson gson = new Gson();
        Type type = new TypeToken<ChamCong>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public ChamCong toListChamCongFromChamCong(String value){
        Gson gson = new Gson();
        Type type = new TypeToken<ChamCong>() {}.getType();
        return gson.fromJson(value, type);
    }


    @TypeConverter
    public String fromChiTietToList(ChiTietChamCong value){
        Gson gson = new Gson();
        Type type = new TypeToken<ChiTietChamCong>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public ChiTietChamCong toListChiTietFromChiTiet(String value){
        Gson gson = new Gson();
        Type type = new TypeToken<ChiTietChamCong>() {}.getType();
        return gson.fromJson(value, type);
    }

    @TypeConverter
    public String fromSanPhamToList(List<SanPham> value){
        Gson gson = new Gson();
        Type type = new TypeToken<List<SanPham>>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public List<SanPham> toListSanPhamFromSanPham(String value){
        Gson gson = new Gson();
        Type type = new TypeToken<List<SanPham>>() {}.getType();
        return gson.fromJson(value, type);
    }


}
