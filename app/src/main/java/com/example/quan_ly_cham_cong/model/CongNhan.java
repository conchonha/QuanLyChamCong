package com.example.quan_ly_cham_cong.model;




import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;


@Entity
public class CongNhan implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String maCN;
    private String HoCN;
    private String TenCN;
    private String PhanXuong;
    private ChamCong chamCong;

    public CongNhan(String maCN, String hoCN, String tenCN, String phanXuong, ChamCong chamCong) {
        this.maCN = maCN;
        HoCN = hoCN;
        TenCN = tenCN;
        PhanXuong = phanXuong;
        this.chamCong = chamCong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaCN() {
        return maCN;
    }

    public void setMaCN(String maCN) {
        this.maCN = maCN;
    }

    public String getHoCN() {
        return HoCN;
    }

    public void setHoCN(String hoCN) {
        HoCN = hoCN;
    }

    public String getTenCN() {
        return TenCN;
    }

    public void setTenCN(String tenCN) {
        TenCN = tenCN;
    }

    public String getPhanXuong() {
        return PhanXuong;
    }

    public void setPhanXuong(String phanXuong) {
        PhanXuong = phanXuong;
    }

    public ChamCong getChamCong() {
        return chamCong;
    }

    public void setChamCong(ChamCong chamCong) {
        this.chamCong = chamCong;
    }

    public CongNhan(){}
}
