package com.semaproject.chatapp2021;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            Thread.sleep(4000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this,LoginActivity.class));
        finish();

    }
}