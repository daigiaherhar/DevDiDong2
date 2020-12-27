package com.example.doan.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.doan.Model.KhachHang;
import com.example.doan.Model.PhatSinh;

import java.util.ArrayList;

public class DBPhatSinh {
    DBHelp dbHelp;
    SQLiteDatabase database;

    public DBPhatSinh(Context context) {
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

    public void them(PhatSinh phatSinh) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("sophieu", phatSinh.getSoPhieu());
        values.put("ngaylap", phatSinh.getNgayLap());
        values.put("makh", phatSinh.getMaKH());

        db.insert("phatsinh", null, values);

    }
    public void SuaMaKHBangRong(PhatSinh phatSinh) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sophieu",phatSinh.getSoPhieu());

        values.put("makh",phatSinh.getMaKH());



        db.update("khachhang",values,"sophieu ='" + phatSinh.getSoPhieu() + "'",null);

    }
    //xoa kh lan~ phat sinh
    public void xoa(String makh) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        String sql = "Delete from phatsinh where makh= '" + makh + "'";
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

    public ArrayList<PhatSinh> LayDL() {
        ArrayList<PhatSinh> data = new ArrayList<>();
        String sql = "select * from phatsinh";
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            PhatSinh phatSinh = new PhatSinh();
            phatSinh.setSoPhieu(cursor.getString(0));
            phatSinh.setNgayLap(cursor.getString(1));
            phatSinh.setMaKH(cursor.getString(2));


            data.add(phatSinh);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<PhatSinh> TimKiem(String key) {
        ArrayList<PhatSinh> data = new ArrayList<>();
        String sql = "select * from phatsinh where makh LIKE '%"+ key +"%'";
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            PhatSinh phatSinh = new PhatSinh();
            phatSinh.setSoPhieu(cursor.getString(0));
            phatSinh.setNgayLap(cursor.getString(1));
            phatSinh.setMaKH(cursor.getString(2));

            data.add(phatSinh);
        }
        while (cursor.moveToNext());
        return data;
    }
}
