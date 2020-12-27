package com.example.doan.GiaoDien;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.doan.Adapter.KhachHangAdapter;
import com.example.doan.DataBase.DBDichVu;
import com.example.doan.DataBase.DBKhachHang;
import com.example.doan.GiaoDien.Fragment.ListDichVu;
import com.example.doan.GiaoDien.Fragment.Main;
import com.example.doan.GiaoDien.Fragment.SlidePager;
import com.example.doan.Model.KhachHang;
import com.example.doan.R;
import com.example.doan.ThuVien.TenSort;

import java.util.ArrayList;
import java.util.List;

public class DanhSachKH extends AppCompatActivity {
    GridView GVKhachHang;
    Button btnTimKiem;
    EditText txtTimKiem;
    ArrayList<KhachHang> dataKh = new ArrayList<>();

    ArrayAdapter adapter_KH;
    int index = 0;

    List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_k_h);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//        setControl();
//        setEvent();

        fragmentList.add(new Main());
        fragmentList.add(new ListDichVu());
        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePager(getSupportFragmentManager(),fragmentList);
        pager.setAdapter(pagerAdapter);

    }

//    private void setControl() {
//        GVKhachHang = findViewById(R.id.GVKhachHang);
//        txtTimKiem = findViewById(R.id.txtTimKiem);
//        btnTimKiem = findViewById(R.id.btnTimKiem);
//    }
//
//    private void setEvent() {
//        //loading du lieu
//        try {
//            DBKhachHang dbKhachHang = new DBKhachHang(this);
//            dataKh = dbKhachHang.LayDL();
//            adapter_KH = new KhachHangAdapter(this, R.layout.khachhang_, dataKh);
//
//            GVKhachHang.setAdapter(adapter_KH);
//            registerForContextMenu(GVKhachHang);
//        } catch (Exception e) {
//
//        }
//
//
//        btnTimKiem.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View v) {
//                if (txtTimKiem.length() == 0) {
//                    return;
//                } else {
//                    DBKhachHang dbKhachHang = new DBKhachHang(getApplication());
//                    String key = txtTimKiem.getText() + "";
//                    //ArrayList<KhachHang> khachHangs = new ArrayList<>();
//                    dataKh = dbKhachHang.TimKiem(txtTimKiem.getText() + "");
//                    Toast.makeText(getApplication(), key, Toast.LENGTH_SHORT).show();
////                    for (KhachHang asb :
////                            dataKh) {
////                            if(asb.getTen().equals(txtTimKiem.getText()))
////                            {
////                                dataKh.add(asb);
////                            }
////                    }
//                    adapter_KH = new KhachHangAdapter(getApplication(), R.layout.khachhang_, dataKh);
//                    // dataKh.sort(new TenSort());
//                    GVKhachHang.setAdapter(adapter_KH);
//                    adapter_KH.notifyDataSetChanged();
//
//                }
//            }
//        });
//
//        GVKhachHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                KhachHang _khachHang = dataKh.get(position);
//                //Toast.makeText(DanhSachKH.this, model_khachHang.toString(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(DanhSachKH.this, ChiTiecKhachHang.class);
//
//                Bundle bundle = new Bundle();
//                bundle.putString("ThongTin1Khach", _khachHang.toString());
//                intent.putExtras(bundle);
//
//                index = position;
//                startActivity(intent);
//            }
//        });
//
//    }
//

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
