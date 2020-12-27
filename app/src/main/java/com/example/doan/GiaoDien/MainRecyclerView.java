package com.example.doan.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.doan.Adapter.MyRecyclerViewAdapter;
import com.example.doan.DataBase.DBKhachHang;
import com.example.doan.Model.CardViewModel;
import com.example.doan.R;

import java.util.ArrayList;
import java.util.Vector;

public class MainRecyclerView extends AppCompatActivity {
    private ArrayList<CardViewModel> data;
    RecyclerView recyclerView;
    int position = 0;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.recyclerView);
        try {
            //Initiation of data
            DBKhachHang dbKhachHang = new DBKhachHang(this);
            data = dbKhachHang.LayDLCardView();


            //Setup Recycler View
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            //GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(layoutManager);

            MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(R.layout.card_view_layout, data);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {

        }


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

