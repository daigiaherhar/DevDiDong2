package com.example.doan.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doan.Adapter.KhachHangAdapter;
import com.example.doan.DataBase.DBKhachHang;
import com.example.doan.DataBase.DBPhatSinh;
import com.example.doan.DataBase.DBPhatSinhChiTiet;
import com.example.doan.Model.CardViewModel;
import com.example.doan.Model.PhatSinh;
import com.example.doan.R;

import java.util.ArrayList;
import java.util.Vector;

public class KhachHang extends AppCompatActivity {
    ListView listViewKH;
    EditText txtMaKH, txtTenKH, txtNgaySinh, txtDiaChi;
    Button btnThem, btnXoa, btnSua, btnLamSach;
    ImageView imgGioiTinh;
    //ArrayList<KhachHang> dataKH = new ArrayList<>();
    ArrayList<com.example.doan.Model.KhachHang> dataKh = new ArrayList<>();
    ArrayAdapter adapter_KH;


    Vector<CardViewModel> data;
    int gioiTinh = 1;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
//        FrameLayout mainLayout = findViewById(R.id.drawContainer);
//        getLayoutInflater().inflate(R.layout.activity_main, mainLayout);
        setControl();
       setEvent();
    }

    private void setEvent() {
        try {
            DBKhachHang dbKhachHang = new DBKhachHang(this);
            dataKh = dbKhachHang.LayDL();
            adapter_KH = new KhachHangAdapter(this, R.layout.khachhang_, dataKh);

            listViewKH.setAdapter(adapter_KH);
            registerForContextMenu(listViewKH);
        }catch (Exception e){

        }

        listViewKH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                com.example.doan.Model.KhachHang _khachHang = dataKh.get(position);
                txtDiaChi.setText(_khachHang.getDiaChi());
                txtMaKH.setText(_khachHang.getMa());
                txtNgaySinh.setText(_khachHang.getNgaySinh());
                txtTenKH.setText(_khachHang.getTen());
                if(_khachHang.getGioiTinh() == 1)
                {
                    imgGioiTinh.setImageResource(R.drawable.male);
                }else
                {
                    imgGioiTinh.setImageResource(R.drawable.female);
                }
                index = position;
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBKhachHang dbKhachHang = new DBKhachHang(getApplicationContext());

                com.example.doan.Model.KhachHang _khachHang = getKhachHang();
                dbKhachHang.them(_khachHang);
                dataKh.add(_khachHang);
                adapter_KH.notifyDataSetChanged();

            }


        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBKhachHang dbKhachHang = new DBKhachHang(getApplicationContext());
                com.example.doan.Model.KhachHang _khachHang = getKhachHang();
                dbKhachHang.xoa(_khachHang);

//                DBPhatSinh dbPhatSinh = new DBPhatSinh(getApplication());
//
//              //  dbPhatSinh.xoa(_khachHang.getMa());
//
//                ArrayList<PhatSinh> phatSinhs = new ArrayList<>();
//                phatSinhs = dbPhatSinh.TimKiem(_khachHang.getMa());
//
//                //    Toast.makeText(getApplication(),phatSinhs.size() + "",Toast.LENGTH_SHORT).show();
////
//
//
//                DBPhatSinhChiTiet dbPhatSinhChiTiet = new DBPhatSinhChiTiet(getApplication());
//                Toast.makeText(getApplication(),dbPhatSinhChiTiet.LayDL() + "",Toast.LENGTH_SHORT).show();
//                //dbPhatSinhChiTiet.xoa(phatSinhs.get(0).getSoPhieu());
////                dataKh.remove(index);
////                adapter_KH.notifyDataSetChanged();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBKhachHang dbKhachHang = new DBKhachHang(getApplicationContext());
                com.example.doan.Model.KhachHang _khachHang = getKhachHang();
                dbKhachHang.sua(_khachHang);
                dataKh.set(index,_khachHang);
                adapter_KH.notifyDataSetChanged();
            }
        });



        btnLamSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDiaChi.setText("");
                txtMaKH.setText("");
                txtNgaySinh.setText("");
                txtTenKH.setText("");
                imgGioiTinh.setImageResource(R.drawable.male);
            }
        });
        imgGioiTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gioiTinh == 1) {
                    imgGioiTinh.setImageResource(R.drawable.female);
                    // gioiTinh = "Nữ";
                    gioiTinh = 2;
                } else {
                    //gioiTinh = "Nam";
                    imgGioiTinh.setImageResource(R.drawable.male);
                    gioiTinh = 1;
                }
            }
        });


    }

    private com.example.doan.Model.KhachHang getKhachHang() {
        com.example.doan.Model.KhachHang khachhang = new com.example.doan.Model.KhachHang();
        khachhang.setMa(txtMaKH.getText().toString());
        khachhang.setTen(txtTenKH.getText().toString());
        khachhang.setNgaySinh(txtNgaySinh.getText().toString());
        khachhang.setDiaChi(txtDiaChi.getText().toString());
        khachhang.setGioiTinh(gioiTinh);
        return khachhang;
    }


    private void setControl() {
        listViewKH = findViewById(R.id.listViewKH);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);

        btnLamSach = findViewById(R.id.btnLamSach);



        txtMaKH = findViewById(R.id.txtMaKH);
        txtTenKH = findViewById(R.id.txtTenKH);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        txtDiaChi = findViewById(R.id.txtDiaChi);

        imgGioiTinh = findViewById(R.id.imgGioiTinh);
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
