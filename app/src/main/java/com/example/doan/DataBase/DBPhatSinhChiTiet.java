package com.example.doan.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.doan.Model.PhatSinhChiTiet;

import java.util.ArrayList;

public class DBPhatSinhChiTiet {
    DBHelp dbHelp;
    SQLiteDatabase database;

    public DBPhatSinhChiTiet(Context context) {
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

    public void them(PhatSinhChiTiet phatSinhChiTiet) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("sophieu", phatSinhChiTiet.getSoPhieu());
        values.put("madv", phatSinhChiTiet.getMaDV());
        values.put("sotien", phatSinhChiTiet.getSoTien());
        values.put("soLuong", phatSinhChiTiet.getSoLuong());


        db.insert("phatsinhchitiet", null, values);

    }

    public void xoa(String soPhieu) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        String sql = "Delete from phatsinhchitiet where sophieu= '" + soPhieu + "'";
        db.execSQL(sql);

    }
//
//    public void sua(PhatSinhChiTiet modelKhachHang) {
//        SQLiteDatabase db = dbHelp.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("ma",modelKhachHang.getMa());
//        values.put("ten",modelKhachHang.getTen());
//        values.put("ngaySinh",modelKhachHang.getNgaySinh());
//        values.put("diaChi",modelKhachHang.getDiaChi());
//
//        if(modelKhachHang.getGioiTinh() != 1)
//        {
//            values.put("gioiTinh",2);
//        }else {
//            values.put("gioiTinh", 1);
//        }
//        db.update("khachhang",values,"ma ='" +modelKhachHang.getMa() + "'",null);
//
//    }

    public ArrayList<PhatSinhChiTiet> LayDL() {
        ArrayList<PhatSinhChiTiet> data = new ArrayList<>();
        String sql = "select * from phatsinhchitiet";
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            PhatSinhChiTiet phatSinhChiTiet = new PhatSinhChiTiet();
            phatSinhChiTiet.setSoPhieu(cursor.getString(0));
            phatSinhChiTiet.setMaDV(cursor.getString(1));
            phatSinhChiTiet.setSoLuong(cursor.getInt(2));
            phatSinhChiTiet.setSoTien(cursor.getInt(3));



            data.add(phatSinhChiTiet);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<PhatSinhChiTiet> TimKiem(String key) {
        ArrayList<PhatSinhChiTiet> data = new ArrayList<>();
        String sql = "select * from phatsinhchitiet where sophieu LIKE '%"+ key +"%'";
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        do {
            PhatSinhChiTiet phatSinhChiTiet = new PhatSinhChiTiet();
            phatSinhChiTiet.setSoPhieu(cursor.getString(0));
            phatSinhChiTiet.setMaDV(cursor.getString(1));
            phatSinhChiTiet.setSoLuong(cursor.getInt(2));
            phatSinhChiTiet.setSoTien(cursor.getInt(3));

            data.add(phatSinhChiTiet);
        }
        while (cursor.moveToNext());
        return data;
    }
}
