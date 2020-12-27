package com.example.doan.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.doan.Model.DichVu;

import java.util.ArrayList;

public class DBDichVu {
    DBHelp dbHelp;
    SQLiteDatabase database;

    public DBDichVu(Context context) {
        dbHelp = new DBHelp(context);
        try {
            database = dbHelp.getWritableDatabase();
        } catch (SQLException ex) {
            database = dbHelp.getReadableDatabase();
        }
    }


    public void close() {
        dbHelp.close();
    }

    public void them(DichVu dichVu) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("madv", dichVu.getMaDV());
        values.put("tendv", dichVu.getTenDV());
        values.put("dongia", dichVu.getDonGia());
        values.put("soluong", dichVu.getSoLuong());
        values.put("hinhanh", dichVu.getHinhAnh());


        db.insert("dichvu", null, values);

    }

    public void xoa(DichVu dichVu) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        String sql = "Delete from dichvu where madv= '" + dichVu.getMaDV() + "'";
        db.execSQL(sql);

    }

    public void sua(DichVu dichVu) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("madv", dichVu.getMaDV());
        values.put("tendv", dichVu.getTenDV());
        values.put("dongia", dichVu.getDonGia());
        values.put("soluong", dichVu.getSoLuong());
        values.put("hinhanh", dichVu.getHinhAnh());

        db.update("dichvu", values, "madv ='" + dichVu.getMaDV() + "'", null);
    }

    public void SuaDonGia(DichVu dichVu) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("madv", dichVu.getMaDV());
        values.put("dongia", dichVu.getDonGia());
        values.put("soluong", dichVu.getSoLuong());

        db.update("dichvu", values, "madv ='" + dichVu.getMaDV() + "'", null);
    }

    public ArrayList<DichVu> LayDL() {
       ArrayList<DichVu> data = new ArrayList<>();
//        String sql = "select * from dichvu";
//        SQLiteDatabase db = dbHelp.getReadableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        cursor.moveToFirst();
//        do {
//            DichVu dichVu = new DichVu();
//            dichVu.setMaDV(cursor.getString(0));
//            dichVu.setTenDV(cursor.getString(1));
//            dichVu.setDonGia(cursor.getInt(2));
//            dichVu.setSoLuong(cursor.getInt(3));
//            dichVu.setHinhAnh(cursor.getBlob(4));
//
//            data.add(dichVu);
//        }
//        while (cursor.moveToNext());
        return data;

    }

    public ArrayList<DichVu> LayDL(String ma) {
        ArrayList<DichVu> data = new ArrayList<>();
        String sql = "select * from dichvu where madv = '" + ma + "'";
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                DichVu dichVu = new DichVu();
                dichVu.setMaDV(cursor.getString(0));
                dichVu.setTenDV(cursor.getString(1));
                dichVu.setDonGia(cursor.getInt(2));
                dichVu.setSoLuong(cursor.getInt(3));
                dichVu.setHinhAnh(cursor.getBlob(4));

                data.add(dichVu);
            }
            while (cursor.moveToNext());
        } catch (Exception e) {

        }

        return data;
    }
}
