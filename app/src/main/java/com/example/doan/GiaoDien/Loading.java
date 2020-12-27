package com.example.doan.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.doan.R;

public class Loading extends AppCompatActivity {
    ImageView imgDVSC, imgChuot, imgLogo;
    Animation runingChupt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        setControl();
        setEvent();

    }

    private void setEvent() {
        runingChupt = AnimationUtils.loadAnimation(Loading.this,R.anim.chuot_chopchop);
        imgChuot.startAnimation(runingChupt);
        final AnimationDrawable runingcat = (AnimationDrawable) imgDVSC.getDrawable();
        runingcat.start();

        imgChuot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runingChupt = AnimationUtils.loadAnimation(Loading.this, R.anim.chuot);
                imgChuot.startAnimation(runingChupt);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Loading.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },3000);
            }
        });

    }

    private void setControl() {
        imgDVSC = findViewById(R.id.imgDVSC);
        imgLogo = findViewById(R.id.imgLogo);
        imgChuot = findViewById(R.id.imgChuot);
    }
}
