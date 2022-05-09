package com.example.quan_ly_cham_cong.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.adapter.BaoCaoApdater;
import com.example.quan_ly_cham_cong.database.AppDatabase;
import com.example.quan_ly_cham_cong.model.BaoCao;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.write.WritableWorkbook;

public class BaoCaoActivity extends AppCompatActivity {
    private BaoCaoApdater baoCaoApdater;
    AppDatabase database;
    RecyclerView rcv_bao_cao;
    Button them, data,bieudo;
    WritableWorkbook workbook;
    private List<BaoCao> listBaoCao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bc);
        database = AppDatabase.getDatabaseInstance(this);
        listBaoCao = database.baoCaoDAO().getListBaoCao();
        showData();
    }

    private void showData(){
        baoCaoApdater = new BaoCaoApdater();
        if (database.baoCaoDAO().getListBaoCao() != null){
            baoCaoApdater.addBaoCao(database.baoCaoDAO().getListBaoCao());
            rcv_bao_cao = this.findViewById(R.id.rcv_baocao);
            rcv_bao_cao.setAdapter(baoCaoApdater);
            rcv_bao_cao.setLayoutManager(new LinearLayoutManager(this));
        }
        bieudo=this.findViewById(R.id.bieudo);
        bieudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaoCaoActivity.this,BieuDoActivity.class));
            }
        });
        them = this.findViewById(R.id.them);
        data = this.findViewById(R.id.them_data);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(BaoCaoActivity.this, ThemBcActivity.class));
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, 1);
                    } else {
                        if(database.baoCaoDAO().getListBaoCao().size() > 0){
                            createExcelFile();
                        }else {
                            Toast.makeText(BaoCaoActivity.this, "list are empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    if(database.baoCaoDAO().getListBaoCao().size() > 0){
                        createExcelFile();
                    }else {
                        Toast.makeText(BaoCaoActivity.this, "list are empty", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    private void createExcelFile(){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = null;
        sheet = (HSSFSheet) wb.createSheet("App Sheet");
        HSSFCell cell = null;
        Row row = sheet.createRow(0);
        cell = (HSSFCell) row.createCell(0);
        cell.setCellValue("Họ và tên");

        cell = (HSSFCell) row.createCell(1);
        cell.setCellValue("Phân xưởng");

        cell = (HSSFCell) row.createCell(2);
        cell.setCellValue("Thành phẩm");

        cell = (HSSFCell) row.createCell(3);
        cell.setCellValue("áo sơ mi nam");

        cell = (HSSFCell) row.createCell(4);
        cell.setCellValue("áo sơ mi nữ");

        cell = (HSSFCell) row.createCell(5);
        cell.setCellValue("Bao tay");

        cell = (HSSFCell) row.createCell(6);
        cell.setCellValue("giày dép");

        cell = (HSSFCell) row.createCell(7);
        cell.setCellValue("nón");

        cell = (HSSFCell) row.createCell(8);
        cell.setCellValue("Quần tây");


        sheet.setColumnWidth(0, (20 * 200));
        sheet.setColumnWidth(1, (30 * 200));
        sheet.setColumnWidth(2, (30 * 200));
        sheet.setColumnWidth(3, (20 * 200));
        sheet.setColumnWidth(4, (30 * 200));
        sheet.setColumnWidth(5, (30 * 200));
        sheet.setColumnWidth(6, (20 * 200));
        sheet.setColumnWidth(7, (30 * 200));
        sheet.setColumnWidth(8, (30 * 200));

        for (int i=0; i<listBaoCao.size(); i++){
            Row row1 = sheet.createRow(i+1);
            cell = (HSSFCell) row1.createCell(0);
            cell.setCellValue(listBaoCao.get(i).getTen());

            cell = (HSSFCell) row1.createCell(1);
            cell.setCellValue(database.baoCaoDAO().getListBaoCao().get(i).getPhanXuong());

            cell = (HSSFCell) row1.createCell(2);
            cell.setCellValue(database.baoCaoDAO().getListBaoCao().get(i).getTP());

            cell = (HSSFCell) row1.createCell(3);
            cell.setCellValue(database.baoCaoDAO().getListBaoCao().get(i).getSlAoSoMiNam());

            cell = (HSSFCell) row1.createCell(4);
            cell.setCellValue(database.baoCaoDAO().getListBaoCao().get(i).getSlAoSoMiNu());

            cell = (HSSFCell) row1.createCell(5);
            cell.setCellValue(database.baoCaoDAO().getListBaoCao().get(i).getSlBaoTay());

            cell = (HSSFCell) row1.createCell(6);
            cell.setCellValue(database.baoCaoDAO().getListBaoCao().get(i).getSlDep());

            cell = (HSSFCell) row1.createCell(7);
            cell.setCellValue(database.baoCaoDAO().getListBaoCao().get(i).getSlNon());

            cell = (HSSFCell) row1.createCell(8);
            cell.setCellValue(database.baoCaoDAO().getListBaoCao().get(i).getSlQuan());

            sheet.setColumnWidth(0, (20 * 200));
            sheet.setColumnWidth(1, (30 * 200));
            sheet.setColumnWidth(2, (30 * 200));
            sheet.setColumnWidth(3, (20 * 200));
            sheet.setColumnWidth(4, (30 * 200));
            sheet.setColumnWidth(5, (30 * 200));
            sheet.setColumnWidth(6, (20 * 200));
            sheet.setColumnWidth(7, (30 * 200));
            sheet.setColumnWidth(8, (30 * 200));

        }
        String folderName = "App";
        String fileName = folderName + ".xlsx";
        String path = Environment.getExternalStorageDirectory() +  "/" + folderName + "/" + fileName;
        java.io.File file = new File(path);
        Uri destinationUri = Uri.fromFile(file);
        if (file.exists()) {
            file.mkdirs();
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                FileOutputStream outputStream = new FileOutputStream(String.valueOf(destinationUri));
                wb.write(outputStream);
                Toast.makeText(getApplicationContext(), "Excel Created in " + path, Toast.LENGTH_SHORT).show();
                Log.e("toanpk_create_excel", path);
            }
        }catch (Exception e){
            e.printStackTrace();
//            try {
//                outputStream.close();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//
//            }
        }
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (database.baoCaoDAO().getListBaoCao().size() > 0) {
                createExcelFile();
            } else {
                Toast.makeText(BaoCaoActivity.this, "list are empty", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (database.baoCaoDAO().getListBaoCao() != null){
            baoCaoApdater.addBaoCao(database.baoCaoDAO().getListBaoCao());
            rcv_bao_cao = this.findViewById(R.id.rcv_baocao);
            rcv_bao_cao.setAdapter(baoCaoApdater);
            rcv_bao_cao.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}

