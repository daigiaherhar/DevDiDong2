package com.example.doan.Model;

public class PhieuThu {
    String soPhieu;
    String maKH;
    String ngayThu;
    int tongTien;
    int tinhTrang;

    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getNgayThu() {
        return ngayThu;
    }

    public void setNgayThu(String ngayThu) {
        this.ngayThu = ngayThu;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public PhieuThu(String soPhieu, String maKH, String ngayThu, int tongTien, int tinhTrang) {
        this.soPhieu = soPhieu;
        this.maKH = maKH;
        this.ngayThu = ngayThu;
        this.tongTien = tongTien;
        this.tinhTrang = tinhTrang;
    }

    public PhieuThu() {

    }

}
