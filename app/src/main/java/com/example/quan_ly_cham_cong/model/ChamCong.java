package com.example.quan_ly_cham_cong.model;





import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity
public class ChamCong implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String maCC;
    private String ngayCC;
    private String maCN;
    private ChiTietChamCong chiTietChamCong;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaCC() {
        return maCC;
    }

    public void setMaCC(String maCC) {
        this.maCC = maCC;
    }

    public String getNgayCC() {
        return ngayCC;
    }

    public void setNgayCC(String ngayCC) {
        this.ngayCC = ngayCC;
    }

    public String getMaCN() {
        return maCN;
    }

    public void setMaCN(String maCN) {
        this.maCN = maCN;
    }

    public ChiTietChamCong getChiTietChamCong() {
        return chiTietChamCong;
    }

    public void setChiTietChamCong(ChiTietChamCong chiTietChamCong) {
        this.chiTietChamCong = chiTietChamCong;
    }

    public ChamCong(String maCC, String ngayCC, String maCN, ChiTietChamCong chiTietChamCong) {
        this.maCC = maCC;
        this.ngayCC = ngayCC;
        this.maCN = maCN;
        this.chiTietChamCong = chiTietChamCong;
    }
    public ChamCong(){}
}