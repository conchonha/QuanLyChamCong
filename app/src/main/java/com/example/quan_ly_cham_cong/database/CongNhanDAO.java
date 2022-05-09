package com.example.quan_ly_cham_cong.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quan_ly_cham_cong.model.CongNhan;

import java.util.List;

//Annotation này là nơi mình định nghĩa các câu lệnh truy vấn cơ sở dữ liệu
@Dao
public interface CongNhanDAO {
    @Insert
     void insert(CongNhan congNhan);

    @Query("SELECT * FROM congNhan")
     List<CongNhan> getListCongNhan();

    @Update
     void updateCongNhan(CongNhan congNhan);

    @Delete
    void delete(CongNhan congNhan);
}

