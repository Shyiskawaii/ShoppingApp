package com.example.doan.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper{
    //BẢNG
    public static String TB_TaiKhhoan="TaiKhoan";
    public static String TB_KhachHang="KhachHang";
    public static String TB_MauSac="MauSac";
    public static String TB_DanhMucSP="DanhMucSP";
    public static String TB_BoNhoTrong="BoNhoTrong";
    public static String TB_ThuongHieu="ThuongHieu";
    public static String TB_SanPhan="SanPham";
    public static String TB_BienTheSP="BienTheSP";
    // CÁC TRƯỜNG TRONG BẢNG
    //trường trong bảng nhân viên




    public CreateDatabase(@Nullable Context context) {
        super(context, "Moblie", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
