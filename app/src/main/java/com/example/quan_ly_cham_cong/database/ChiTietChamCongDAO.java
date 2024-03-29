package com.example.quan_ly_cham_cong.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.quan_ly_cham_cong.model.ChiTietChamCong;

import java.util.List;

@Dao
public interface ChiTietChamCongDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ChiTietChamCong chiTietChamCong);

    @Query("SELECT * FROM ChiTietChamCong")
    List<ChiTietChamCong> getListChiTietChamCong();


    @Query("SELECT * FROM ChiTietChamCong ORDER BY soTP DESC LIMIT 7")
    LiveData<List<ChiTietChamCong>> getListChiTietChamCongBieuDo();

//    @Query("SELECT * FROM ChiTietChamCong where maSP")
//    LiveData<List<ChiTietChamCong>> getListChiTietChamCongBieuDo();
}
