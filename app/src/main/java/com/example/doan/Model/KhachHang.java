package com.example.doan.Model;

public class KhachHang {
    String ma;
    String ten;
    String ngaySinh;
    String diaChi;
    int gioiTinh;

    public KhachHang(String ma, String ten, String ngaySinh, String diaChi, int gioiTinh) {
        this.ma = ma;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }



    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }
    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }



    @Override
    public String toString() {
        return  "Mã: " + ma +"    "+ "Tên: " + ten +"    "+ "Ngày:" + ngaySinh +"    "+ "Địa chỉ" +"    "+ diaChi + "  " + "Gioi tinh:̉"+ gioiTinh;

        //return gioiTinh + "";
    }


    public KhachHang() {
    }
}
