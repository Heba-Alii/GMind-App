package com.gmind.gmindapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gmind.gmindapp.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {
    private ActivitySplashScreenBinding binding;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(view);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                runActivity();
            }
        };
        handler.postDelayed(runnable, 3 * 1000);
    }

    public void runActivity() {
        Intent intent = new Intent(this, OnBoardingScreen.class);
        startActivity(intent);
        finish();
    }
}