package com.example.quan_ly_cham_cong.database;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.quan_ly_cham_cong.model.BaoCao;
import com.example.quan_ly_cham_cong.model.ChamCong;
import com.example.quan_ly_cham_cong.model.ChiTietChamCong;
import com.example.quan_ly_cham_cong.model.CongNhan;
import com.example.quan_ly_cham_cong.model.SanPham;

//đây là annotation sẽ cho mình biết là sẽ có bao nhiêu bảng được tạo trong sqlite
@Database(entities = {CongNhan.class, ChamCong.class, ChiTietChamCong.class, SanPham.class, BaoCao.class}, version = 2, exportSchema = true)
@TypeConverters(AppTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    //đây là các abstract giúp cho công việc truy vấn trở lên dễ dàng hơn
    public abstract CongNhanDAO congNhanDAO();
    public abstract ChamCongDAO chamCongDAO();
    public abstract SanPhamDAO sanPhamDAO();
    public abstract ChiTietChamCongDAO chiTietChamCongDAO();
    public abstract BaoCaoDAO baoCaoDAO();

    //thuộc tính với hàm này giúp mình lấy được instance của class này
    public static AppDatabase DATABASE_INSTANCE;
    public static AppDatabase getDatabaseInstance(Context context){
       if (DATABASE_INSTANCE == null){
           DATABASE_INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                   AppDatabase.class,
                   "app_database")
                   .allowMainThreadQueries()
                   .build();
       }
       return DATABASE_INSTANCE;
   }
}
