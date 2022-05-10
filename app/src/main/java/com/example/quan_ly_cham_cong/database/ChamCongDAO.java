package com.example.quan_ly_cham_cong.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quan_ly_cham_cong.model.ChamCong;
import com.example.quan_ly_cham_cong.model.ChiTietChamCong;

import java.util.List;

@Dao
public interface ChamCongDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChamCong(ChamCong chamCong);

    @Query("SELECT * FROM CHAMCONG")
    List<ChamCong> listChamCong();


}
