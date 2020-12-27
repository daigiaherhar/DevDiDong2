package com.example.doan.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.doan.Model.PhatSinhChiTiet;
import com.example.doan.Model.PhieuThu;

import java.util.ArrayList;

public class DBPhieuThu {
    DBHelp dbHelp;
    SQLiteDatabase database;

    public DBPhieuThu(Context context) {
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

    public void them(PhieuThu phieuThu) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("sophieu", phieuThu.getSoPhieu());
        values.put("makh", phieuThu.getMaKH());
        values.put("ngaythu", phieuThu.getNgayThu());
        values.put("tongtien", phieuThu.getTongTien());
        values.put("tinhTrang", phieuThu.isTinhTrang());


        db.insert("phieuthu", null, values);

    }


    public void sua(PhieuThu phieuThu) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sophieu",phieuThu.getSoPhieu());
        values.put("tinhTrang",phieuThu.isTinhTrang());
        values.put("tongtien",phieuThu.getTongTien());


        db.update("phieuthu",values,"sophieu ='" +phieuThu.getSoPhieu() + "'",null);

    }

    public ArrayList<PhieuThu> LayDL() {
        ArrayList<PhieuThu> data = new ArrayList<>();
        String sql = "select * from phieuthu";
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            PhieuThu phieuThu = new PhieuThu();
            phieuThu.setSoPhieu(cursor.getString(0));
            phieuThu.setMaKH(cursor.getString(1));
            phieuThu.setNgayThu(cursor.getString(2));
            phieuThu.setTongTien(cursor.getInt(3));
            phieuThu.setTinhTrang(cursor.getInt(4));

            data.add(phieuThu);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<PhieuThu> TimKiem(String key) {
        ArrayList<PhieuThu> data = new ArrayList<>();
        String sql = "select * from phieuthu where sophieu LIKE '%"+ key +"%'";
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            PhieuThu phieuThu = new PhieuThu();
            phieuThu.setSoPhieu(cursor.getString(0));
            phieuThu.setMaKH(cursor.getString(1));
            phieuThu.setNgayThu(cursor.getString(2));
            phieuThu.setTongTien(cursor.getInt(3));
            phieuThu.setTinhTrang(cursor.getInt(4));

            data.add(phieuThu);
        }
        while (cursor.moveToNext());
        return data;
    }

}
