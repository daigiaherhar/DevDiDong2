package com.example.doan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doan.Model.KhachHang;
import com.example.doan.R;

import java.util.ArrayList;

public class KhachHangAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<KhachHang> data;

    public KhachHangAdapter(Context context, int resource, ArrayList<KhachHang> data) {
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
        ImageView imgGioiTinh;
        TextView tvMa;
        TextView tvTen;
        TextView tvDiaChi;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);

            holder.tvMa = view.findViewById(R.id.tvMa);
            holder.tvTen = view.findViewById(R.id.tvTen);
            holder.tvDiaChi = view.findViewById(R.id.tvDiaChi);
            holder.imgGioiTinh = view.findViewById(R.id.imgGioiTinh1);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        KhachHang _khachHang = data.get(position);
        if(_khachHang.getGioiTinh() == 1)
            holder.imgGioiTinh.setImageResource(R.drawable.male);
        if(_khachHang.getGioiTinh() == 2)
            holder.imgGioiTinh.setImageResource(R.drawable.female);

        holder.tvMa.setText(_khachHang.getMa());
        holder.tvTen.setText(_khachHang.getTen());
        holder.tvDiaChi.setText(_khachHang.getDiaChi());

        return view;
    }

}
