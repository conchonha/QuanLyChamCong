package com.example.quan_ly_cham_cong.network;

import com.example.quan_ly_cham_cong.model.ResultGoogleSheet;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiIpList {
    @FormUrlEncoded
    @POST("exec")
    Call<ResultGoogleSheet> postGoogleSheet(@Field("action") String action,
                                            @Field("maCC") String maCC,
                                            @Field("ngayCC") String ngayCC,
                                            @Field("maSP") String maSP,
                                            @Field("tenSP") String tenSP,
                                            @Field("soSP") String soSP,
                                            @Field("soPP") String soPP,
                                            @Field("tienCong") String tienCong,
                                            @Field("thanhTien") String thanhTien);
}
