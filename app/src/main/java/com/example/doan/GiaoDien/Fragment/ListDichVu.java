package com.example.doan.GiaoDien.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan.Adapter.DichVuAdapter;
import com.example.doan.DataBase.DBDichVu;
import com.example.doan.Model.DichVu;
import com.example.doan.R;

import java.util.ArrayList;

public class ListDichVu extends Fragment {
    ListView lvListDichVu;
    ArrayList<DichVu> dichVus;
    ArrayAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (ViewGroup)inflater.inflate((R.layout.fragment_dichvu),container,false);
        lvListDichVu = rootView.findViewById(R.id.lvListDichVu);
        DBDichVu dbDichVu = new DBDichVu(getActivity());
        dichVus = dbDichVu.LayDL();
        adapter = new DichVuAdapter(getActivity(),R.layout.dichvu_adapter,dichVus);
        lvListDichVu.setAdapter(adapter);
        return rootView;
    }
}
