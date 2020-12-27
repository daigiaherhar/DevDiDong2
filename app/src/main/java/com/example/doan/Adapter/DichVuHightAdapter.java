package com.example.doan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doan.GiaoDien.ChiTietDichVu;
import com.example.doan.Model.DichVu;
import com.example.doan.Model.PhatSinh;
import com.example.doan.R;

import java.util.ArrayList;

public class DichVuHightAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<DichVu> data;


    public DichVuHightAdapter(Context context, int resource, ArrayList<DichVu> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    static class Holder {
        ImageView imgHinhA;
        TextView txtSoluongDichvu;
        TextView txtGia;
        TextView tvTen;
        ImageView imgMua;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Holder holder = new Holder();
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);

            holder.txtSoluongDichvu = view.findViewById(R.id.txtSoluongDichvu);
            holder.txtGia = view.findViewById(R.id.txtGia);
            holder.imgMua = view.findViewById(R.id.imgMua);
            holder.tvTen = view.findViewById(R.id.tvTen);
            holder.imgHinhA = view.findViewById(R.id.imgHinhAdapter);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final DichVu dichVu = data.get(position);
        //final PhatSinh phatSinh = data_phatSinh.get(position);

        byte[] hinhAnh = dichVu.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        holder.imgHinhA.setImageBitmap(bitmap);

        holder.txtSoluongDichvu.setText(dichVu.getSoLuong() + "");
        holder.tvTen.setText(dichVu.getTenDV() + "");

        holder.txtGia.setText(dichVu.getDonGia() + "");
        holder.imgMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Activity) context, ChiTietDichVu.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", dichVu.getMaDV());
               // bundle.putString("sophieu", phatSinh.getSoPhieu());
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);
            }
        });
        return view;
    }

}
