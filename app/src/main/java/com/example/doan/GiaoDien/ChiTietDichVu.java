package com.example.doan.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.DataBase.DBDichVu;
import com.example.doan.DataBase.DBKhachHang;
import com.example.doan.DataBase.DBPhatSinh;
import com.example.doan.DataBase.DBPhatSinhChiTiet;
import com.example.doan.DataBase.DBPhieuThu;
import com.example.doan.GiaoDien.Fragment.ManHinhChinh;
import com.example.doan.Model.DichVu;
import com.example.doan.Model.KhachHang;
import com.example.doan.Model.PhatSinhChiTiet;
import com.example.doan.Model.PhieuThu;
import com.example.doan.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ChiTietDichVu extends AppCompatActivity {

    EditText txtGiaChiTiet;
    Button btnThem;
    TextView txtTenDVChiTiet;
    ImageView imgHinhChiTiet;
    Spinner spSoLuong;

    ArrayList<String> soLuong = new ArrayList<>();
    ArrayAdapter ap_soL;
    ArrayList<DichVu> data_DV = new ArrayList<>();
    String madv = "";
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dich_vu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setConTrol();
        setEvent();


    }


    private void setEvent() {
        String ma = getIntent().getExtras().getString("ma");
        DBDichVu dbDichVu = new DBDichVu(this);
        data_DV = dbDichVu.LayDL(ma);
        txtTenDVChiTiet.setText(data_DV.get(0).getTenDV());
        madv = ma;
        txtGiaChiTiet.setText(data_DV.get(0).getDonGia() + "");
        for(int i = 1;i<=data_DV.get(0).getSoLuong();i++)
        {
            soLuong.add(i + "");
        }
        ap_soL = new ArrayAdapter(this,android.R.layout.simple_list_item_1,soLuong);
        spSoLuong.setAdapter(ap_soL);
        byte[] hinhAnh = data_DV.get(0).getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        imgHinhChiTiet.setImageBitmap(bitmap);
        final int donGia = data_DV.get(0).getDonGia();

        spSoLuong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtGiaChiTiet.setText((position+1) * donGia +"");
                index = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplication(),MainActivity.soPhieuPhatSinh+"",Toast.LENGTH_SHORT).show();
                DBPhatSinhChiTiet dbPhatSinhChiTiet = new DBPhatSinhChiTiet(getApplication());
                PhatSinhChiTiet phatSinhChiTiet = getPhatSinh();
                dbPhatSinhChiTiet.them(phatSinhChiTiet);

                PhieuThu phieuThu = new PhieuThu();
                DBPhieuThu dbphieuThu = new DBPhieuThu(getApplication());
                phieuThu = getPhieuThu();
                dbphieuThu.them(phieuThu);

                finish();
            }
        });
    }
    private PhatSinhChiTiet getPhatSinh() {
        PhatSinhChiTiet phatSinhChiTiet = new PhatSinhChiTiet();
        phatSinhChiTiet.setSoPhieu(ManHinhChinh.soPhieuPhatSinh+"");
        phatSinhChiTiet.setMaDV(txtTenDVChiTiet.getText().toString());
        phatSinhChiTiet.setSoTien(Integer.parseInt(txtGiaChiTiet.getText()+""));
        phatSinhChiTiet.setSoLuong(index);
        return phatSinhChiTiet;
    }
    private PhieuThu getPhieuThu() {


        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        PhieuThu phieuThu = new PhieuThu();
        phieuThu.setSoPhieu(ManHinhChinh.soPhieuPhatSinh + "");
        phieuThu.setNgayThu(date);
        phieuThu.setTinhTrang(0);

        phieuThu.setMaKH(ManHinhChinh.maKH + "");

        return phieuThu;
    }
    private void setConTrol() {

        txtGiaChiTiet = findViewById(R.id.txtGia);
        txtTenDVChiTiet = findViewById(R.id.tvTenDV);
        imgHinhChiTiet = findViewById(R.id.imgHinhChiTiet);
        btnThem = findViewById(R.id.btnThem);
        spSoLuong = findViewById(R.id.spSoluong);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
