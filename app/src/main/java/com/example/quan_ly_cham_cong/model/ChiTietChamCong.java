package com.example.quan_ly_cham_cong.model;




import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;


//Entity là khởi tạo 1 bảng trong database sqlite nếu để như này thì mặc định tên bảng là tên class
//trong bảng tên các cột sẽ trùng với tên của thuộc tính trong class
@Entity
public class ChiTietChamCong implements Serializable {
    //primaryKey là khóa chính để auto nó tự tăng lên 1 đơn vị đối với mỗi thực thể
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String maCC;
    private String maSP;
    private int soTP;
    private int soPP;
    private List<SanPham> listSanPham;

    public ChiTietChamCong(int id, String maCC, String maSP, int soTP, int soPP, List<SanPham> listSanPham) {
        this.id = 0;
        this.maCC = maCC;
        this.maSP = maSP;
        this.soTP = soTP;
        this.soPP = soPP;
        this.listSanPham = listSanPham;
    }
    public ChiTietChamCong(){}

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

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoTP() {
        return soTP;
    }

    public void setSoTP(int soTP) {
        this.soTP = soTP;
    }

    public int getSoPP() {
        return soPP;
    }

    public void setSoPP(int soPP) {
        this.soPP = soPP;
    }

    public List<SanPham> getListSanPham() {
        return listSanPham;
    }

    public void setListSanPham(List<SanPham> listSanPham) {
        this.listSanPham = listSanPham;
    }
}
