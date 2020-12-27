package com.example.doan.Model;

import java.util.Arrays;

public class DichVu {
    String maDV;
    String tenDV;
    int donGia;
    int soLuong;
    byte[] hinhAnh;

    @Override
    public String toString() {
        return "DichVu{" +
                "maDV='" + maDV + '\'' +
                ", tenDV='" + tenDV + '\'' +
                ", donGia=" + donGia +
                ", soLuong=" + soLuong +
//                ", hinhAnh=" + Arrays.toString(hinhAnh) +
                '}';
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public DichVu(String maDV, String tenDV, int donGia, int soLuong, byte[] hinhAnh) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
    }

    public DichVu() { }
}
