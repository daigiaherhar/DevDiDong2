package com.example.doan.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doan.Model.DichVu;
import com.example.doan.R;

import java.util.ArrayList;

public class ListAnhAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<DichVu> data;

    public ListAnhAdapter(Context context, int resource, ArrayList<DichVu> data) {
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
        ImageView imgListAnh;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Holder holder = new Holder();
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);

            holder.imgListAnh = view.findViewById(R.id.imgListAnh);

            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        DichVu dichVu = data.get(position);


        byte[] hinhAnh = dichVu.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imgListAnh.setImageBitmap(bitmap);


        return view;
    }
}
