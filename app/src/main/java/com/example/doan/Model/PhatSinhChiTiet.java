package com.example.doan.Model;

public class PhatSinhChiTiet {
    String soPhieu;
    String maDV;
    int soLuong;
    int soTien;

    @Override
    public String toString() {
        return "PhatSinhChiTiet{" + "soPhieu='" + soPhieu + '\'' + ", maDV='" + maDV + '\'' + ", soLuong=" + soLuong + ", soTien=" + soTien + '}';

    }
//    return "Mãdv:" + maDV+ "  " + "SL:" +soLuong +"  " +"Tiền:" +soTien+"\n";
//    "PhatSinhChiTiet{" + "soPhieu='" + soPhieu + '\'' + ", maDV='" + maDV + '\'' + ", soLuong=" + soLuong + ", soTien=" + soTien + '}';
    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }



    public PhatSinhChiTiet(String soPhieu, String maDV, int soLuong, int soTien) {
        this.soPhieu = soPhieu;
        this.maDV = maDV;
        this.soLuong = soLuong;
        this.soTien = soTien;
    }
    public PhatSinhChiTiet() {

    }
}
