package com.example.doan.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.doan.Model.CardViewModel;
import com.example.doan.Model.KhachHang;

import java.util.ArrayList;

public class DBKhachHang {
    DBHelp dbHelp;
    SQLiteDatabase database;

    public DBKhachHang(Context context) {
        dbHelp = new DBHelp(context);
        try {
            database = dbHelp.getWritableDatabase();
        } catch (SQLException ex) {
            database = dbHelp.getReadableDatabase();
        }
    }

    public DBKhachHang() {

    }


    public void close() {
        dbHelp.close();
    }

    public void them(KhachHang modelKhachHang) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("ma", modelKhachHang.getMa());
        values.put("ten", modelKhachHang.getTen());
        values.put("ngaySinh", modelKhachHang.getNgaySinh());
        values.put("diaChi", modelKhachHang.getDiaChi());
        values.put("gioiTinh", modelKhachHang.getGioiTinh());


        db.insert("khachhang", null, values);

    }

    public void xoa(KhachHang modelKhachHang) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        String sql = "Delete from khachhang where ma= '" + modelKhachHang.getMa() + "'";
        db.execSQL(sql);

    }

    public void sua(KhachHang modelKhachHang) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ma",modelKhachHang.getMa());
        values.put("ten",modelKhachHang.getTen());
        values.put("ngaySinh",modelKhachHang.getNgaySinh());
        values.put("diaChi",modelKhachHang.getDiaChi());

        if(modelKhachHang.getGioiTinh() != 1)
        {
            values.put("gioiTinh",2);
        }else {
            values.put("gioiTinh", 1);
        }
        db.update("khachhang",values,"ma ='" +modelKhachHang.getMa() + "'",null);

    }

    public ArrayList<KhachHang> LayDL() {
        ArrayList<KhachHang> data = new ArrayList<>();
        String sql = "select * from khachhang";
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            KhachHang khachhang = new KhachHang();
            khachhang.setMa(cursor.getString(0));
            khachhang.setTen(cursor.getString(1));
            khachhang.setNgaySinh(cursor.getString(2));
            khachhang.setDiaChi(cursor.getString(3));
            khachhang.setGioiTinh(cursor.getInt(4));

            data.add(khachhang);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<KhachHang> TimKiem(String key) {
        ArrayList<KhachHang> data = new ArrayList<>();
        String sql = "select * from khachhang where ten LIKE '%"+ key +"%'";
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        do {
            KhachHang khachhang = new KhachHang();
            khachhang.setMa(cursor.getString(0));
            khachhang.setTen(cursor.getString(1));
            khachhang.setNgaySinh(cursor.getString(2));
            khachhang.setDiaChi(cursor.getString(3));
            khachhang.setGioiTinh(cursor.getInt(4));

            data.add(khachhang);
        }
        while (cursor.moveToNext());
        return data;
    }

    public ArrayList<KhachHang> TimKiemMa(String key) {
        ArrayList<KhachHang> data = new ArrayList<>();
        String sql = "select * from khachhang where ma LIKE '%"+ key +"%'";
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        do {
            KhachHang khachhang = new KhachHang();
            khachhang.setMa(cursor.getString(0));
            khachhang.setTen(cursor.getString(1));
            khachhang.setNgaySinh(cursor.getString(2));
            khachhang.setDiaChi(cursor.getString(3));
            khachhang.setGioiTinh(cursor.getInt(4));

            data.add(khachhang);
        }
        while (cursor.moveToNext());
        return data;
    }

    public ArrayList<CardViewModel> LayDLCardView() {
        ArrayList<CardViewModel> data = new ArrayList<>();
        String sql = "select * from khachhang";
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            CardViewModel khachhang = new CardViewModel();
            khachhang.setCardName(cursor.getString(1));

            khachhang.setImageResourceId(cursor.getInt(4));

            data.add(khachhang);
        }
        while (cursor.moveToNext());

        return data;
    }
}
