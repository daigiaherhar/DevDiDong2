package com.example.doan.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doan.Adapter.DichVuHightAdapter;
import com.example.doan.DataBase.DBDichVu;
import com.example.doan.DataBase.DBPhatSinh;
import com.example.doan.DataBase.DBPhatSinhChiTiet;
import com.example.doan.DataBase.DBPhieuThu;
import com.example.doan.GiaoDien.Fragment.ManHinhChinh;
import com.example.doan.Model.DichVu;
import com.example.doan.Model.PhatSinh;
import com.example.doan.Model.PhatSinhChiTiet;
import com.example.doan.Model.PhieuThu;
import com.example.doan.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TaoPhieu_MuaHang extends AppCompatActivity {
    ListView lvLuaChonDichVu;
    Button btnThanhToan;
    ArrayList<DichVu> arrDV = new ArrayList<>();
    // ArrayList<PhatSinh> arrPS = new ArrayList<>();
    ArrayList<PhieuThu> arrPT = new ArrayList<>();
    ArrayAdapter adapterDV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_phieu__mua_hang);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setControl() {
        lvLuaChonDichVu = findViewById(R.id.lvLuaChonDichVu);
        btnThanhToan = findViewById(R.id.btnThanhToan);
    }

    private void setEvent() {
        try {
            DBDichVu dbDichVu = new DBDichVu(getApplication());
            arrDV = dbDichVu.LayDL();

            adapterDV = new DichVuHightAdapter(this, R.layout.dichvu_adapter_hight, arrDV);
            lvLuaChonDichVu.setAdapter(adapterDV);

        } catch (Exception e) {

        }
        lvLuaChonDichVu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int soPhieu = ManHinhChinh.soPhieuPhatSinh;
                Toast.makeText(getApplication(), "Phiếu số: " + soPhieu + "", Toast.LENGTH_SHORT).show();
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DBPhieuThu dbPhieuThu = new DBPhieuThu(getApplication());

                AlertDialog.Builder builder = new AlertDialog.Builder(TaoPhieu_MuaHang.this);
                builder.setTitle("Xác nhận thanh toán");
                builder.setMessage("Bạn có thực sự muốn thanh toàn không?");
                builder.setPositiveButton("Thanh toán", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            PhieuThu phieuThu = new PhieuThu();
                            phieuThu = getPhieuThu();
                            dbPhieuThu.sua(phieuThu);

//                            arrPT = dbPhieuThu.TimKiem(MainActivity.soPhieuPhatSinh + "");
                            finish();

                            Toast.makeText(getApplicationContext(), "Đã thanh toán", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(), arrPT.size() + "", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                        }

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
        });
    }

    private PhieuThu getPhieuThu() {
        PhieuThu phieuThu = new PhieuThu();
        phieuThu.setSoPhieu(ManHinhChinh.soPhieuPhatSinh + "");
        phieuThu.setTinhTrang(1);

        return phieuThu;
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
