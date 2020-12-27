package com.example.doan.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.doan.R;

public class ChiTiecKhachHang extends AppCompatActivity {
    GridView GVSuaChua;
    TextView tvThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiec_khach_hang);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String info = bundle.getString("ThongTin1Khach");
        tvThongTin.setText(info);

    }

    private void setControl() {
        tvThongTin = findViewById(R.id
                .tvThongTin);
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
