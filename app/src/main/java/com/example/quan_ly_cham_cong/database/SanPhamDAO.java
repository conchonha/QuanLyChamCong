package com.example.quan_ly_cham_cong.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quan_ly_cham_cong.model.SanPham;

import java.util.List;

@Dao
public interface SanPhamDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSanPham(SanPham sanPham);

    @Query("SELECT * FROM SanPham")
    List<SanPham> getListSP();

    @Update
    void updateSP(SanPham...sanPhams);

    @Delete
    void deleteSp(SanPham sanPham);
}
