package com.example.doan.GiaoDien.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.doan.Adapter.KhachHangAdapter;
import com.example.doan.DataBase.DBKhachHang;
import com.example.doan.GiaoDien.ChiTiecKhachHang;
import com.example.doan.GiaoDien.DanhSachKH;
import com.example.doan.Model.KhachHang;
import com.example.doan.R;

import java.util.ArrayList;

public class Main extends Fragment {
    GridView GVKhachHang;
    Button btnTimKiem;
    EditText txtTimKiem;
    ArrayList<KhachHang> dataKh = new ArrayList<>();

    ArrayAdapter adapter_KH;
    int index = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate((R.layout.fragment_danhsachkh), container, false);

        GVKhachHang = rootView.findViewById(R.id.GVKhachHang);
        txtTimKiem = rootView.findViewById(R.id.txtTimKiem);
        btnTimKiem = rootView.findViewById(R.id.btnTimKiem);

        try {
            DBKhachHang dbKhachHang = new DBKhachHang(getContext());
            dataKh = dbKhachHang.LayDL();
            adapter_KH = new KhachHangAdapter(getActivity(), R.layout.khachhang_, dataKh);

            GVKhachHang.setAdapter(adapter_KH);
            registerForContextMenu(GVKhachHang);
        } catch (Exception e) {

        }

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                try {

                    if (txtTimKiem.length() == 0) {
                        return;
                    } else {
                        DBKhachHang dbKhachHang = new DBKhachHang(getContext());
                        String key = txtTimKiem.getText() + "";

                        dataKh = dbKhachHang.TimKiem(txtTimKiem.getText() + "");
                        Toast.makeText(getContext(), key, Toast.LENGTH_SHORT).show();
                        adapter_KH = new KhachHangAdapter(getActivity(), R.layout.khachhang_, dataKh);

                        GVKhachHang.setAdapter(adapter_KH);
                        adapter_KH.notifyDataSetChanged();


                    }
                }catch (Exception e)
                {
                    Toast.makeText(getActivity(), "Không tìm thấy", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }

}
