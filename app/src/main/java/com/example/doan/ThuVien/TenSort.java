package com.example.doan.ThuVien;

import com.example.doan.Model.KhachHang;

import java.util.Comparator;

public class TenSort implements Comparator<KhachHang> {
    @Override
    public int compare(KhachHang o1, KhachHang o2) {
        return o2.getTen().compareTo(o1.getTen());
    }
}
