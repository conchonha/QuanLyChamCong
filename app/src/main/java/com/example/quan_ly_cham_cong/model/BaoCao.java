package com.example.quan_ly_cham_cong.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BaoCao {
    @PrimaryKey(autoGenerate = true)
    private int idBaoCao;
    private String ten;
    private String phanXuong;
    private int TP;
    private int slAoSoMiNam;
    private int slAoSoMiNu;
    private int slBaoTay;
    private int slDep;
    private int slNon;
    private int slQuan;

    public BaoCao(){}
    public int getIdBaoCao() {
        return idBaoCao;
    }

    public void setIdBaoCao(int idBaoCao) {
        this.idBaoCao = idBaoCao;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPhanXuong() {
        return phanXuong;
    }

    public void setPhanXuong(String phanXuong) {
        this.phanXuong = phanXuong;
    }

    public int getTP() {
        return TP;
    }

    public void setTP(int TP) {
        this.TP = TP;
    }

    public int getSlAoSoMiNam() {
        return slAoSoMiNam;
    }

    public void setSlAoSoMiNam(int slAoSoMiNam) {
        this.slAoSoMiNam = slAoSoMiNam;
    }

    public int getSlAoSoMiNu() {
        return slAoSoMiNu;
    }

    public void setSlAoSoMiNu(int slAoSoMiNu) {
        this.slAoSoMiNu = slAoSoMiNu;
    }

    public int getSlBaoTay() {
        return slBaoTay;
    }

    public void setSlBaoTay(int slBaoTay) {
        this.slBaoTay = slBaoTay;
    }

    public int getSlDep() {
        return slDep;
    }

    public void setSlDep(int slDep) {
        this.slDep = slDep;
    }

    public int getSlNon() {
        return slNon;
    }

    public void setSlNon(int slNon) {
        this.slNon = slNon;
    }

    public int getSlQuan() {
        return slQuan;
    }

    public void setSlQuan(int slQuan) {
        this.slQuan = slQuan;
    }

    public BaoCao(int idBaoCao, String ten, String phanXuong, int TP, int slAoSoMiNam, int slAoSoMiNu, int slBaoTay, int slDep, int slNon, int slQuan) {
        this.idBaoCao = idBaoCao;
        this.ten = ten;
        this.phanXuong = phanXuong;
        this.TP = TP;
        this.slAoSoMiNam = slAoSoMiNam;
        this.slAoSoMiNu = slAoSoMiNu;
        this.slBaoTay = slBaoTay;
        this.slDep = slDep;
        this.slNon = slNon;
        this.slQuan = slQuan;
    }
}
