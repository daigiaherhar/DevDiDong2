package com.example.doan.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.Adapter.DichVuHightAdapter;
import com.example.doan.DataBase.DBKhachHang;
import com.example.doan.DataBase.DBPhatSinh;
import com.example.doan.DataBase.DBPhatSinhChiTiet;
import com.example.doan.DataBase.DBPhieuThu;
import com.example.doan.Model.PhatSinh;
import com.example.doan.Model.PhatSinhChiTiet;
import com.example.doan.Model.PhieuThu;
import com.example.doan.R;

import java.util.ArrayList;

public class PhieuSuaChua extends AppCompatActivity {
    Button btnInHoaDon;
    EditText txtMaKHPhieuSuaChua, txtTenKH, txtNgaySinh, txtDiaChi;
    ImageView imgGioiTinh;
    ListView lvPhatSinh;
    ArrayList<PhatSinh> dataPhatSinh = new ArrayList<>();
    ArrayList<PhatSinhChiTiet> dataPhatSinhChiTiet = new ArrayList<>();
    ArrayList<PhieuThu> dataPhieuThu = new ArrayList<>();
    ArrayAdapter adapter_phatsinh;
    ArrayList<com.example.doan.Model.KhachHang> arrKH = new ArrayList<>();

    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_sua_chua);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setControl();
        setEvent();
    }

    private void setEvent() {
//        try {
//            DBPhatSinh dbPhatSinh = new DBPhatSinh(this);
//            dataPhatSinh = dbPhatSinh.LayDL();
//            adapter_phatsinh = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataPhatSinh);
//
//            lvPhatSinh.setAdapter(adapter_phatsinh);
//
//        } catch (Exception e) {
//
//        }
        btnInHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PhieuSuaChua.this, InHoaDon.class);
                startActivity(intent);
            }
        });
        lvPhatSinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                    DBPhatSinhChiTiet dbPhatSinhChiTiet = new DBPhatSinhChiTiet(getApplication());
                    DBPhatSinh dbKhachHang = new DBPhatSinh(getApplication());
                    //kiếm mã số phiếu của danh sánh khi mình click, -> position
                    dataPhatSinh = dbKhachHang.TimKiem(txtMaKHPhieuSuaChua.getText() + "");
                    //bất đầu tìm kiếm, in ra danh sách theo số phiếu
                    dataPhatSinhChiTiet = dbPhatSinhChiTiet.TimKiem(dataPhatSinh.get(position).getSoPhieu() + "");

                    index = position;
                    DBPhieuThu dbPhieuThu = new DBPhieuThu(getApplication());
                    dataPhieuThu = dbPhieuThu.TimKiem(dataPhatSinh.get(position).getSoPhieu() + "");

                    int tinhTrang = 0;
                    int tongTien = 0;
                    String c = "";
                    String b = "";
                    for (int i = 0; i < dataPhatSinhChiTiet.size(); i++) {
                        b += i + 1 + ". MãDv:" + dataPhatSinhChiTiet.get(i).getMaDV() +
                                "   SL:" + dataPhatSinhChiTiet.get(i).getSoLuong() +
                                "   Tiền:" + dataPhatSinhChiTiet.get(i).getSoTien() + "\n";
                        tongTien += dataPhatSinhChiTiet.get(i).getSoTien();
                    }


                       if(dataPhieuThu.get(0).isTinhTrang() == 0)
                       {
                           c += "Chưa thanh toán!!!!";
                       }else
                       {
                           c += "Đã thanh toán";
                       }


                    AlertDialog.Builder builder = new AlertDialog.Builder(PhieuSuaChua.this);
                    String a = "(Số phiếu: " + dataPhatSinh.get(position).getSoPhieu() + ")";
                    builder.setTitle("Phiếu phát sinh chi tiết" + a);

                    builder.setMessage(b + "\t\t\t\tTổng tiền: " + tongTien + "\t\t\t\t" + c);


                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } catch (Exception e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PhieuSuaChua.this);
                    builder.setTitle("Phiếu phát sinh chi tiết");
                    builder.setMessage("Không có dịch vụ");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }

            }
        });

        lvPhatSinh.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final CharSequence[] items = {"Thanh toán", "Thoát"};
                AlertDialog.Builder builder = new AlertDialog.Builder(PhieuSuaChua.this);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(PhieuSuaChua.this);
                            builder.setTitle("Xác nhận thanh toán");
                            builder.setMessage("Bạn có thực sự muốn thanh toàn không?");
                            builder.setPositiveButton("Thanh toán", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DBPhatSinh dbKhachHang = new DBPhatSinh(getApplication());
                                    //kiếm mã số phiếu của danh sánh khi mình click, -> position
                                    dataPhatSinh = dbKhachHang.TimKiem(txtMaKHPhieuSuaChua.getText() + "");
                                    //bất đầu tìm kiếm, in ra danh sách theo số phiếu

                                    DBPhieuThu dbPhieuThu = new DBPhieuThu(getApplication());
                                    dataPhieuThu = dbPhieuThu.TimKiem(dataPhatSinh.get(index).getSoPhieu() + "");
                                    PhieuThu phieuThu = new PhieuThu();

                                    phieuThu.setSoPhieu(dataPhieuThu.get(0).getSoPhieu() + "");
                                    phieuThu.setTinhTrang(1);

                                    dbPhieuThu.sua(phieuThu);
                                }
                            });
                            builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();
                        }
                    }
                });

                builder.show();
                //Toast.makeText(getApplicationContext(), "concac", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void setControl() {
        btnInHoaDon = findViewById(R.id.btnInHoaDon);
        lvPhatSinh = findViewById(R.id.lvPhatSinh);

        imgGioiTinh = findViewById(R.id.imgGioiTinh);

        txtMaKHPhieuSuaChua = findViewById(R.id.txtMaKHPhieuSuaChua);
        txtTenKH = findViewById(R.id.txtTenKH);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        txtDiaChi = findViewById(R.id.txtDiaChi);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_phieusuachua, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menuTimKiem:
                if (txtMaKHPhieuSuaChua.length() == 0) {
                    break;
                } else {
                    try {
                        DBPhatSinh dbKhachHang = new DBPhatSinh(getApplication());
                        String key = txtMaKHPhieuSuaChua.getText() + "";
                        //ArrayList<KhachHang> khachHangs = new ArrayList<>();
                        dataPhatSinh = dbKhachHang.TimKiem(txtMaKHPhieuSuaChua.getText() + "");
                        Toast.makeText(getApplication(), key, Toast.LENGTH_SHORT).show();


                        DBKhachHang dbKhachHang1 = new DBKhachHang(getApplication());
                        arrKH = dbKhachHang1.TimKiemMa(txtMaKHPhieuSuaChua.getText() + "");
                        txtTenKH.setText(arrKH.get(0).getTen());
                        txtNgaySinh.setText(arrKH.get(0).getNgaySinh());
                        txtDiaChi.setText(arrKH.get(0).getDiaChi());
                        if(arrKH.get(0).getGioiTinh() == 1)
                        {
                            imgGioiTinh.setImageResource(R.drawable.male);
                        }else
                        {
                            imgGioiTinh.setImageResource(R.drawable.female);
                        }

                        adapter_phatsinh = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataPhatSinh);
                        // dataKh.sort(new TenSort());
                        lvPhatSinh.setAdapter(adapter_phatsinh);
                        adapter_phatsinh.notifyDataSetChanged();
                    } catch (Exception e) {
                        Toast.makeText(getApplication(), "Không tìm thấy", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    int TimKiem(ArrayList<PhatSinh> arr, String key) {
        for (int i = 0; i < dataPhatSinh.size(); i++) {
            if (arr.get(i).getMaKH() == key) {
                return i;
            }
        }
        return -1;
    }
}
