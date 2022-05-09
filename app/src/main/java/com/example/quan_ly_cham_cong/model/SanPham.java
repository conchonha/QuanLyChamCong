package com.example.quan_ly_cham_cong.model;




import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class SanPham implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String maSP;
    private String tenSP;
    private float donGia;

    public String getMaSP() {
        return maSP;
    }

    public SanPham(String maSP, String tenSP, float donGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
    }
    public SanPham(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "id=" + id +
                ", maSP='" + maSP + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", donGia=" + donGia +
                '}';
    }
}
