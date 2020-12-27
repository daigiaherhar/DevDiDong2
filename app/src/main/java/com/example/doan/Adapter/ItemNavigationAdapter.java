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

import com.example.doan.Model.ItemNavigation;
import com.example.doan.Model.KhachHang;
import com.example.doan.R;

import java.util.ArrayList;

public class ItemNavigationAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ItemNavigation> data;

    public ItemNavigationAdapter(Context context, int resource, ArrayList<ItemNavigation> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        ImageView imgNavigation;
        TextView tieuDeNavigation;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);

            holder.tieuDeNavigation = view.findViewById(R.id.tieuDeNavigation);

            holder.imgNavigation = view.findViewById(R.id.imgNavigation);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        ItemNavigation itemNavigation = data.get(position);
        holder.imgNavigation.setImageResource(itemNavigation.getIcon());
        holder.tieuDeNavigation.setText(itemNavigation.getTieuDe());

        return view;
    }
}
