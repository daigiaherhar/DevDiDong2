package com.example.doan.Adapter;

import android.graphics.drawable.Drawable;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Model.CardViewModel;
import com.example.doan.R;

import java.util.ArrayList;
import java.util.Vector;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private int layoutID;
    private ArrayList<CardViewModel> data;

    public MyRecyclerViewAdapter(int layoutID, ArrayList<CardViewModel> data) {
        this.layoutID = layoutID;
        this.data = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
       private ImageView imageView;
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =  itemView.findViewById(R.id.imgView);
            textView =  itemView.findViewById(R.id.imgText);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        CardView viewItem = (CardView) inflater.inflate(layoutID, viewGroup, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        CardViewModel cardViewModel = data.get(i);
       // Drawable drawable = viewHolder.imageView.getResources().getDrawable(cardViewModel.getImageResourceId());
        if(cardViewModel.getImageResourceId() == 1)
        {
            viewHolder.imageView.setImageResource(R.drawable.male);
        }else{
            viewHolder.imageView.setImageResource(R.drawable.female);
        }

        viewHolder.textView.setText(cardViewModel.getCardName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
