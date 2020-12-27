package com.example.doan.GiaoDien;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;

import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.Adapter.DichVuAdapter;
import com.example.doan.Adapter.DichVuHightAdapter;
import com.example.doan.Adapter.ItemNavigationAdapter;
import com.example.doan.DataBase.DBDichVu;
import com.example.doan.DataBase.DBKhachHang;
import com.example.doan.DataBase.DBPhatSinh;
import com.example.doan.GiaoDien.Fragment.ListDichVu;
import com.example.doan.GiaoDien.Fragment.Main;
import com.example.doan.GiaoDien.Fragment.ManHinhChinh;
import com.example.doan.GiaoDien.Fragment.SlidePager;
import com.example.doan.Model.ItemNavigation;
import com.example.doan.Model.PhatSinh;
import com.example.doan.Model.PhatSinhChiTiet;
import com.example.doan.R;
import com.example.doan.ThuVien.MyAdmin;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listNavigation, lvDichVuHight;

    FrameLayout mainLayout;

    List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    ArrayList<com.example.doan.Model.DichVu> arrDV = new ArrayList<>();
    ArrayList<com.example.doan.Model.PhatSinh> arrPS = new ArrayList<>();
    ArrayList<com.example.doan.Model.KhachHang> arrKH = new ArrayList<>();
    ArrayAdapter adapterDV;


    ArrayList<ItemNavigation> navigationNames = new ArrayList<>();
    ArrayAdapter adapter_navigation;

    int navigationID = 0;
    int loi = 0;
    DrawerLayout drawerLayout;

    ActionBarDrawerToggle drawerToggle;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.keo_navagation); // lưu ý ở đây
        FrameLayout mainLayout = findViewById(R.id.drawContainer);
        getLayoutInflater().inflate(R.layout.activity_main, mainLayout);

        setControl();
        setToggleDraw();
//
       setEvent();

        fragmentList.add(new ManHinhChinh());
        fragmentList.add(new ListDichVu());

        pager = findViewById(R.id.pagerMain);
        pagerAdapter = new SlidePager(getSupportFragmentManager(),fragmentList);
        pager.setAdapter(pagerAdapter);
    }

    private void setEvent() {
        dienThongTinNavigation();

        listNavigation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navigationID = position;
                if (navigationID == 0) {
                    drawerLayout.closeDrawer(listNavigation);
                }
                if (navigationID == 1) {

                    Intent intent = new Intent(MainActivity.this, DanhSachKH.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(listNavigation);
                }
                if (navigationID == 2) {

                    Intent intent = new Intent(MainActivity.this, PhieuSuaChua.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(listNavigation);
                }
                if (navigationID == 3) {

                    Intent intent = new Intent(MainActivity.this, KhachHang.class);
                    startActivity(intent);

                    drawerLayout.closeDrawer(listNavigation);

                }
                if (navigationID == 4) {

                    Intent intent = new Intent(MainActivity.this, DichVu.class);
                    startActivity(intent);

                    drawerLayout.closeDrawer(listNavigation);

                }
            }
        });
    }


    private void dienThongTinNavigation() {

        navigationNames.add(new ItemNavigation("Trang chủ", R.drawable.trangchu));
        navigationNames.add(new ItemNavigation("Danh sách khách hàng", R.drawable.danhsachkh));
        navigationNames.add(new ItemNavigation("Phiếu sửa chữa", R.drawable.showrecycler));
        navigationNames.add(new ItemNavigation("Thêm khách hàng", R.drawable.themkhachhang));
        navigationNames.add(new ItemNavigation("Thêm dịch vụ", R.drawable.themdichvu));


        adapter_navigation = new ItemNavigationAdapter(this, R.layout.navigation, navigationNames);
        listNavigation.setAdapter(adapter_navigation);
        registerForContextMenu(listNavigation);
    }


    private void setControl() {

        listNavigation = findViewById(R.id.draw);
        drawerLayout = findViewById(R.id.drawLayout);
        mainLayout = findViewById(R.id.drawContainer);
    }

    private void setToggleDraw() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.btnSua, R.string.btnThem) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
