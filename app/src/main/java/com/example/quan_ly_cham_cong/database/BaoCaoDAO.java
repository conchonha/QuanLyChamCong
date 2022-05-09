package com.example.quan_ly_cham_cong.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quan_ly_cham_cong.model.BaoCao;

import java.util.List;

@Dao
public interface BaoCaoDAO {
    @Insert
    void insert(BaoCao baoCao);

    @Query("SELECT * FROM BAOCAO")
    List<BaoCao> getListBaoCao();
}
